package com.currencyapp.app.controllers;

import com.currencyapp.app.model.JsonRateModel;
import com.currencyapp.app.services.RateService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@RestController
public class RateRestController {


    private RateService rateService;
    public RateRestController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping(value = "/process")
    public String processData(@RequestParam String table, @RequestParam String code,
                              @RequestParam String startDate, @RequestParam String endDate) {
        return rateService.getModel(table, code, LocalDate.parse(startDate), LocalDate.parse(endDate));
    }
}
