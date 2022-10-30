package com.santhoshi.currencyConversionService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange",url = "localhost:8080")
@FeignClient(name="currency-exchange-service")//url is not required here , it will ask eureka server and get the
//load balanced url
public interface OurFeignProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getFeignClient(@PathVariable("from") String from , @PathVariable("to") String to);
}
