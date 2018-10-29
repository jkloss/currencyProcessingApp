package com.currencyapp.app.exceptions.handlers;

import com.currencyapp.app.exceptions.MoreOrLessThanThreeLettersException;
import com.currencyapp.app.exceptions.WrongDateException;
import com.currencyapp.app.exceptions.WrongFormatException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionRateHandler {

    @ExceptionHandler(value = {MoreOrLessThanThreeLettersException.class,
            WrongFormatException.class, WrongDateException.class})
    public ModelAndView handleException(RuntimeException exception) {
        if (exception instanceof MoreOrLessThanThreeLettersException) {
            return new ModelAndView("threeLettersError");
        } else if (exception instanceof WrongDateException) {
            return new ModelAndView("wrongDateError");
        } else {
            return new ModelAndView("wrongFormatError");
        }
    }

}
