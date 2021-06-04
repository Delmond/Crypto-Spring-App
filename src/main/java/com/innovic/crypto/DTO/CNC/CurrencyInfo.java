package com.innovic.crypto.DTO.CNC;

public class CurrencyInfo {
    private Long id;
    private String name;
    private String symbol;
    private String slug;
    private ConversionValues quote;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSymbol() {
        return symbol;
    }
    public String getSlug() {
        return slug;
    }
    public ConversionValues getQuote() {
        return quote;
    }

}
