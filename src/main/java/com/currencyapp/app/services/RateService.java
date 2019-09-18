package com.currencyapp.app.services;

import com.currencyapp.app.model.RateModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class RateService {

    private static final String CORE_URL = "http://api.nbp.pl/api/exchangerates/rates/c/";

    private final RestTemplate restTemplate;

    private RateModel getRateModelObject(final String code, final LocalDate startDate, final LocalDate endDate) {
        return restTemplate.getForObject(CORE_URL + code + "/"
                + startDate + "/" + endDate, RateModel.class);
    }

    private BigDecimal getAverageBuyRate(final String code, final LocalDate startDate, final LocalDate endDate) {
        final List<BigDecimal> listOfNumbersConvertedToBD = getRateModelObject(code, startDate, endDate).getRates().stream()
                .map(rate -> new BigDecimal(String.valueOf(rate.getBid())))
                .collect(toList());

        final BigDecimal sum = getSumOfBigDecimalCollection(listOfNumbersConvertedToBD);

        return sum.divide(new BigDecimal(listOfNumbersConvertedToBD.size()), RoundingMode.HALF_UP)
                .setScale(4, RoundingMode.HALF_UP);

    }

    private BigDecimal getSellStandardDeviation(final String code, final LocalDate startDate, final LocalDate endDate) {

        final List<BigDecimal> askValues = getRateModelObject(code, startDate, endDate).getRates().stream()
                .map(rate -> new BigDecimal(String.valueOf(rate.getAsk())))
                .collect(toList());

        BigDecimal standardDeviation = BigDecimal.ZERO;
        final int listLength = askValues.size();

        final BigDecimal sumOfAskValues = getSumOfBigDecimalCollection(askValues);

        final BigDecimal quotient = sumOfAskValues.divide(BigDecimal.valueOf(listLength), RoundingMode.HALF_UP);

        for (BigDecimal num : askValues) {
            standardDeviation = standardDeviation.add((num.subtract(quotient)).pow(2));
        }

        return (standardDeviation.divide(BigDecimal.valueOf(listLength), RoundingMode.HALF_UP))
                .sqrt(new MathContext(10))
                .setScale(4, RoundingMode.HALF_UP);

    }

    private BigDecimal getSumOfBigDecimalCollection(final List<BigDecimal> bigDecimalList) {
        return bigDecimalList.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<String, BigDecimal> getStandardDeviationAndAverageMap(final String code, final LocalDate startDate, final LocalDate endDate) {
        final Map<String, BigDecimal> map = new HashMap<>();
        map.put("standardDeviation", getSellStandardDeviation(code, startDate, endDate));
        map.put("average", getAverageBuyRate(code, startDate, endDate));

        return map;
    }
}
