package com.in28minutes.limitsserviceh2configClient.controller;

import com.in28minutes.limitsserviceh2configClient.ConfigProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    ConfigProps cfg;

    @GetMapping("/getAllProperties")
    public ConfigProps getAllProps(){
    return cfg;
    }

}
