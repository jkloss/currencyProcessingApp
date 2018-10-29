package com.currencyapp.app.services;

import com.currencyapp.app.model.RateModel;
import org.springframework.boot.web.client.RestTemplateBuilder;
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
public class RateService {

    private static final String CORE_URL = "http://api.nbp.pl/api/exchangerates/rates/c/";

    private RestTemplate restTemplate;

    public RateService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private RateModel getRateModelObject(String code, LocalDate startDate, LocalDate endDate) {
        return restTemplate.getForObject(CORE_URL + code + "/"
                + startDate + "/" + endDate, RateModel.class);
    }

    private BigDecimal getAverageBuyRate(String code, LocalDate startDate, LocalDate endDate) {
        List<BigDecimal> listOfNumbersConvertedToBD = getRateModelObject(code, startDate, endDate).getRates().stream()
                .map(r -> new BigDecimal(String.valueOf(r.getBid())))
                .collect(toList());

        BigDecimal sum = listOfNumbersConvertedToBD.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return sum.divide(new BigDecimal(listOfNumbersConvertedToBD.size()), RoundingMode.HALF_UP)
                .setScale(5, RoundingMode.HALF_UP);

    }

    private BigDecimal getSellStandardDeviation(String code, LocalDate startDate, LocalDate endDate) {

        List<BigDecimal> askValues = getRateModelObject(code, startDate, endDate).getRates().stream()
                .map(v -> new BigDecimal(String.valueOf(v.getAsk())))
                .collect(toList());

        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal standardDeviation = BigDecimal.ZERO;
        int listLength = askValues.size();

        for (BigDecimal num : askValues) {
            sum = sum.add(num);
        }

        BigDecimal quotient = sum.divide(BigDecimal.valueOf(listLength), RoundingMode.HALF_UP);

        for (BigDecimal num : askValues) {
            standardDeviation = standardDeviation.add((num.subtract(quotient)).pow(2));
        }

        return (standardDeviation.divide(BigDecimal.valueOf(listLength), RoundingMode.HALF_UP))
                .sqrt(new MathContext(10))
                .setScale(5, RoundingMode.HALF_UP);

    }

    public Map<String, BigDecimal> getStandardDeviationAndAverageMap(String code, LocalDate startDate,
                                                                 LocalDate endDate) {
        Map<String, BigDecimal> map = new HashMap<>();
        map.put("standardDeviation", getSellStandardDeviation(code, startDate, endDate));
        map.put("average", getAverageBuyRate(code, startDate, endDate));

        return map;
    }
}
