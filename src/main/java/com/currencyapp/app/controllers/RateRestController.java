package com.currencyapp.app.controllers;

import com.currencyapp.app.exceptions.MoreOrLessThanThreeLettersException;
import com.currencyapp.app.exceptions.WrongDateException;
import com.currencyapp.app.exceptions.WrongFormatException;
import com.currencyapp.app.services.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RateRestController {

    private final RateService rateService;

    @GetMapping(value = "/process", produces = "application/json")
    public ModelAndView processData(final @RequestParam String code, final @RequestParam String startDate,
                                    final @RequestParam String endDate) {

        final LocalDate firstDate = LocalDate.parse(endDate);
        final LocalDate secondDate = LocalDate.parse(startDate);

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

        final Map<String, BigDecimal> standardDeviationAndAverageMap = rateService.getStandardDeviationAndAverageMap(code,
                LocalDate.parse(startDate), LocalDate.parse(endDate));

        return new ModelAndView("resultView", "valuesToDisplay", standardDeviationAndAverageMap);
    }
}
