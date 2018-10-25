package com.currencyapp.app.controllers;

import com.currencyapp.app.exceptions.WrongDateException;
import com.currencyapp.app.services.RateService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Map;

@RestController
public class RateRestController {

    private RateService rateService;
    public RateRestController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping(value = "/process", produces = "application/json")
    public ModelAndView processData(@RequestParam String code, @RequestParam String startDate,
                                    @RequestParam String endDate) {
        LocalDate date = LocalDate.parse(endDate);

        if (date.isBefore(LocalDate.parse(startDate))) {
            throw new WrongDateException();
        }

        Map<String, Double> standardDeviationAndAverageMap = rateService.getStandardDeviationAndAverageMap(code,
                LocalDate.parse(startDate), LocalDate.parse(endDate));
        return new ModelAndView("resultView", "valuesToDisplay", standardDeviationAndAverageMap);
    }

    @ExceptionHandler(value = WrongDateException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    private ModelAndView getWrongDateException() {
        return new ModelAndView("wrongDateError");
    }
}
