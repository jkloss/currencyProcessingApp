package com.currencyapp.app.services;

import com.currencyapp.app.model.RateModel;
import com.currencyapp.app.model.Rate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

import static java.util.stream.Collectors.toList;

@Service
public class RateService {

    private static final String CORE_URL = "http://api.nbp.pl/api/exchangerates/rates";

    private RestTemplate restTemplate;

    public RateService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private RateModel getRateObject(String table, String code, LocalDate startDate, LocalDate endDate) {
        return restTemplate.getForObject(CORE_URL + "/" + table + "/" + code + "/"
                + startDate + "/" + endDate, RateModel.class);

    }

    private Double getAverageBoughtRate(String table, String code, LocalDate startDate, LocalDate endDate) {
        OptionalDouble averageAsOptional =  getRateObject(table, code, startDate, endDate).getRates().stream()
                .mapToDouble(Rate::getBid)
                .average();

        if (averageAsOptional.isPresent()) {
            return averageAsOptional.getAsDouble();
        } else {
            throw new RuntimeException();
        }
    }

    private Double getSoldStandardDeviation(String table, String code,
                                            LocalDate startDate, LocalDate endDate) {
        List<Double> askValues = getRateObject(table, code, startDate, endDate).getRates().stream()
                .map(Rate::getAsk)
                .collect(toList());

        double sum = 0.0;
        double standardDeviation = 0.0;
        int listLength = askValues.size();

        for (double num : askValues) {
            sum = sum + num;
        }

        double quotient = sum / listLength;

        for (double num : askValues) {
            standardDeviation = standardDeviation + Math.pow(num - quotient, 2);
        }

        return Math.sqrt(standardDeviation / listLength);
    }

    public Map<String, Double> getStandardDeviationAndAverageMap(String table, String code,
                                                                 LocalDate startDate, LocalDate endDate) {
        Map<String, Double> map = new HashMap<>();
        map.put("standardDeviation", getSoldStandardDeviation(table, code, startDate, endDate));
        map.put("average", getAverageBoughtRate(table, code, startDate, endDate));

        return map;
    }
}
