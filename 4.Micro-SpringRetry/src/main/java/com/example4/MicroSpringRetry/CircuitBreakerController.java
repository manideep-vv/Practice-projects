package com.example4.MicroSpringRetry;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {

    long starttime=0;
    int x=0;
    @GetMapping("/sampleAPI")
//    @Retry(name="sample-api" ,fallbackMethod = "m1FallBackMethod")
//    @CircuitBreaker(name = "error-api",fallbackMethod = "m1FallBackMethod")
    @RateLimiter(name = "error-api")
    public String sampleAPI(){
        if(starttime==0){
            starttime=System.currentTimeMillis();
        }
        System.out.printf("hitting--> %d time @ %d seconds %n",x,System.currentTimeMillis()-starttime);
        x++;
//        System.out.println(10/0);
        return "Hello -world-no erros";
    }
    public String m1FallBackMethod(Throwable e){
        System.out.println("cant retry anymore--> executing Fallback method");
        return "1";
    }


}
