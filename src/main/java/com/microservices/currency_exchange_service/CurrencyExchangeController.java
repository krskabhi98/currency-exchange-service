package com.microservices.currency_exchange_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retriveExchangeValue(
            @PathVariable String from,
            @PathVariable String to
    ) {

        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        if (currencyExchange == null) {
            throw new RuntimeException("did not find data");
        } else {
            return currencyExchange;
        }


    }
}
