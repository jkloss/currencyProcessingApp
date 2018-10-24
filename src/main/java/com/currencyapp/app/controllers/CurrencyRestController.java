package com.currencyapp.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyRestController {

    private static final String CORE_API_URL = "http://api.nbp.pl/api/exchangerates";


}
