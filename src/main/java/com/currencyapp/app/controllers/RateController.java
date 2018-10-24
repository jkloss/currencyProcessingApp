package com.currencyapp.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RateController {

    @GetMapping("/")
    public String getMainMenuView() {
        return "mainMenuView";
    }

    @GetMapping("/credentials")
    public String getCredentialsForm() {
        return "credentialsHome";
    }
}
