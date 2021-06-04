package com.innovic.crypto.DTO;

import java.util.Set;

public class WalletBalance {

    private Set<CryptoCurencyBalance> cryptoCurrencies;
    private Double funds;

    public Set<CryptoCurencyBalance> getCryptoCurrencies() {
        return cryptoCurrencies;
    }
    public Double getFunds() {
        return funds;
    }
}
