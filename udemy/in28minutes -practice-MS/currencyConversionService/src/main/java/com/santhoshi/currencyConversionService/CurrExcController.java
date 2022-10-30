package com.santhoshi.currencyConversionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@RequestMapping("/currency-conversion")
public class CurrExcController {


    @Autowired
    Environment env;

    RestTemplate rt;

    @Autowired
    OurFeignProxy proxy;

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchange getExchValue(@PathVariable  String from,@PathVariable  String to){
        String property = env.getProperty("local.server.port");
        System.out.printf("firing controller from port %s",property);

        return new CurrencyExchange(from,to,new BigDecimal(65), property);
    }
    @GetMapping("withOutFeign/from/{from}/to/{to}")
    public CurrencyExchange withOutFeign(@PathVariable  String from,@PathVariable  String to){
    rt=new RestTemplate();
        HashMap<String,String> hm=new HashMap<>();
        hm.put("from",from);
        hm.put("to",to);

        CurrencyExchange svcResponse = rt.getForObject("http://localhost:8080/currency-exchange/from/{from}/to/{to}", CurrencyExchange.class, hm);

        String property = env.getProperty("local.server.port");
        System.out.printf("firing controller from port %s",property);

        return svcResponse;
    }

    @GetMapping("withFeign/from/{from}/to/{to}")
    public CurrencyExchange withFeign(@PathVariable  String from,@PathVariable  String to){
    rt=new RestTemplate();
        HashMap<String,String> hm=new HashMap<>();
        hm.put("from",from);
        hm.put("to",to);

//        CurrencyExchange svcResponse = rt.getForObject("http://localhost:8080/currency-exchange/from/{from}/to/{to}", CurrencyExchange.class, hm);
        CurrencyExchange feignClient = proxy.getFeignClient(from, to);

        String property = env.getProperty("local.server.port");
        System.out.printf("firing controller from port %s",property);

        return feignClient;
    }



}
