package com.currencyapp.app.controllers;

import com.currencyapp.app.services.RateService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("http://api.nbp.pl/api/exchangerates/rates")
public class RateRestController {

    private RateService rateService;
    public RateRestController(RateService rateService) {
        this.rateService = rateService;
    }

    @PostMapping(value = "/{table}/{code}/{startDate}/{endDate}", produces = "application/json")
    public String processData(@PathVariable String table, @PathVariable String code,
                              @PathVariable String startDate, @PathVariable String endDate,
                              Model model) {

        return "resultView";
    }
}
