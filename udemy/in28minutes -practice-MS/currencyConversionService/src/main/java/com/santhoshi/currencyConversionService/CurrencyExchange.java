package com.santhoshi.currencyConversionService;

import java.math.BigDecimal;

public class CurrencyExchange {
    int id;
    String from;
    String to;
    BigDecimal exchangeValue;

    String port;

    public CurrencyExchange(String from, String to, BigDecimal exchangeValue, String port) {
        this.from = from;
        this.to = to;
        this.exchangeValue = exchangeValue;
        this.port = port;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public CurrencyExchange() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getExchangeValue() {
        return exchangeValue;
    }

    public void setExchangeValue(BigDecimal exchangeValue) {
        this.exchangeValue = exchangeValue;
    }
}
