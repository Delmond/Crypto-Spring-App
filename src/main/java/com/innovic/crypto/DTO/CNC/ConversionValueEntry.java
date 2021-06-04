package com.innovic.crypto.DTO.CNC;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConversionValueEntry {
    private Double price;

    @JsonProperty("market_cap")
    private Double marketCap;

    public Double getPrice() {
        return price;
    }
    public Double getMarketCap() {
        return marketCap;
    }
}
