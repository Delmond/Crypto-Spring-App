package com.innovic.crypto.DTO;

public class CryptoCurencyBalance {
    private Long cryptoCurrencyId;
    private String name;
    private Double ammount;
    private Double price;

    public Double getAmmount() {
        return ammount;
    }
    public Long getCryptoCurrencyId() {
        return cryptoCurrencyId;
    }
    public String getName() {
        return name;
    }
    public Double getPrice() {
        return price;
    }
}
