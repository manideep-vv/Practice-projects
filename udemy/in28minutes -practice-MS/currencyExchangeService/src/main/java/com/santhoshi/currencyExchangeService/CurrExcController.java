package com.santhoshi.currencyExchangeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-exchange")
public class CurrExcController {


    @Autowired
    Environment env;

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchange getExchValue(@PathVariable  String from,@PathVariable  String to){
        String property = env.getProperty("local.server.port");
        System.out.printf("firing controller from port %s",property);

        return new CurrencyExchange(from,to,new BigDecimal(65), property);
    }
}
