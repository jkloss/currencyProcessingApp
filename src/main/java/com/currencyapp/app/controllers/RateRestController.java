package com.currencyapp.app.controllers;

import com.currencyapp.app.model.JsonRateModel;
import com.currencyapp.app.services.RateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.OptionalDouble;

@RestController
public class RateRestController {

    private RateService rateService;
    public RateRestController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping(value = "/process", produces = "application/json")
    public ModelAndView processData(@RequestParam String table, @RequestParam String code,
                                    @RequestParam String startDate, @RequestParam String endDate) {

        Double average = rateService.getAverageBoughtRate(table, code, LocalDate.parse(startDate), LocalDate.parse(endDate));
        return new ModelAndView("resultView", "average", average);
    }
}
