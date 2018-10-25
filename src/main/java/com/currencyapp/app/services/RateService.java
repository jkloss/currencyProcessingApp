package com.currencyapp.app.services;

import com.currencyapp.app.model.JsonRateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class RateService {

    private static final String CORE_URL = "http://api.nbp.pl/api/exchangerates/rates";

    private RestTemplate restTemplate;
    public RateService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getModel(String table, String code, LocalDate startDate, LocalDate endDate) {
        return restTemplate.getForObject(CORE_URL + "/" + table + "/" + code + "/"
        + startDate + "/" + endDate + "?format=json", String.class);
    }
}
