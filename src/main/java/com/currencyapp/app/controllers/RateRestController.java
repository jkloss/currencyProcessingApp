package com.currencyapp.app.controllers;

import com.currencyapp.app.exceptions.MoreOrLessThanThreeLettersException;
import com.currencyapp.app.exceptions.WrongDateException;
import com.currencyapp.app.exceptions.WrongFormatException;
import com.currencyapp.app.services.RateService;
import org.springframework.http.HttpStatus;
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

        LocalDate firstDate = LocalDate.parse(endDate);
        LocalDate secondDate = LocalDate.parse(startDate);

        if (firstDate.isBefore(LocalDate.parse(startDate)) || firstDate.isAfter(LocalDate.now())
                || secondDate.isAfter(LocalDate.now())) {
            throw new WrongDateException();
        }

        if (!code.matches("^[A-Za-z]+$")) {
            throw new WrongFormatException();
        }

        if (code.length() != 3) {
            throw new MoreOrLessThanThreeLettersException();
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
    @ExceptionHandler(value = WrongFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ModelAndView getWrongFormatException() {
        return new ModelAndView("wrongFormatError");
    }
    @ExceptionHandler(value = MoreOrLessThanThreeLettersException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ModelAndView getThreeLettersException() {
        return new ModelAndView("threeLettersError");
    }
}
