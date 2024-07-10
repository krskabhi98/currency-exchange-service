package com.microservices.currency_exchange_service;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class circuitBreakerController {
    Logger logger = LoggerFactory.getLogger(CircuitBreakerConfig.class);

    @GetMapping("/sample-api")
    @Retry(name ="sample-api", fallbackMethod = "hardCodeResponse")
    public String sampleApi() {

        logger.info("Sample API call");
        new RestTemplate().getForEntity("http://localhost:8089/some-dummy-url", String.class);

        return "sample API";
    }


    public String hardCodeResponse(Exception ex){

        return "fallback-response";
    }
}
