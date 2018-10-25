package com.currencyapp.app.services;

import com.currencyapp.app.model.JsonRateModel;
import com.currencyapp.app.model.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.OptionalDouble;

@Service
public class RateService {

    private static final String CORE_URL = "http://api.nbp.pl/api/exchangerates/rates";

    private RestTemplate restTemplate;

    public RateService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private JsonRateModel getRateObject(String table, String code, LocalDate startDate, LocalDate endDate) {
        return restTemplate.getForObject(CORE_URL + "/" + table + "/" + code + "/"
                + startDate + "/" + endDate, JsonRateModel.class);

    }

    public Double getAverageBoughtRate(String table, String code, LocalDate startDate, LocalDate endDate) {
        OptionalDouble averageAsOptional =  getRateObject(table, code, startDate, endDate).getRates().stream()
                .mapToDouble(Rate::getBid)
                .average();

        if (averageAsOptional.isPresent()) {
            return averageAsOptional.getAsDouble();
        } else {
            throw new RuntimeException();
        }
    }

    public Double getStandardDeviation(String table, String code,
                                       LocalDate startDate, LocalDate endDate) {
        return null;
    }
}
