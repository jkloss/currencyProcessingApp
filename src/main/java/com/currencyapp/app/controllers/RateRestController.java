package com.currencyapp.app.controllers;

import com.currencyapp.app.services.RateService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("http://api.nbp.pl/api/exchangerates/rates")
public class RateRestController {

    private RateService rateService;
    public RateRestController(RateService rateService) {
        this.rateService = rateService;
    }

    @PostMapping(value = "/process")
    public String processData(@RequestParam String table, @RequestParam String code,
                              @RequestParam String startDate, @RequestParam String endDate) {

        return "resultView";
    }
}
