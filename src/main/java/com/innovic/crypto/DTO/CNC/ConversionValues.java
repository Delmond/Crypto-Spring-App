package com.innovic.crypto.DTO.CNC;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConversionValues {
    @JsonProperty("USD")
    private ConversionValueEntry usd;

    public ConversionValueEntry getUsd() {
        return usd;
    }
}
