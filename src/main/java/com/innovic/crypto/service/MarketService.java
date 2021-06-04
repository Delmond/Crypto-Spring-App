package com.innovic.crypto.service;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

import com.innovic.crypto.model.CryptoCurrency;
import com.innovic.crypto.model.Market;
import com.innovic.crypto.repository.CryptoCurrencyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MarketService {

    @Autowired
    private CryptoCurrencyRepository cryptoCurrencyRepository;


    private Market findLatestMarket(Set<Market> changes){
        return changes.stream()
                      .max(Comparator.comparing(Market::getTimestamp))
                      .orElse(null);
    }


    public Market getLatestMarketByCryptoCurrencyId(Long cryptoCurrencyId){
        Optional<CryptoCurrency> optionalCryptoCurrency = cryptoCurrencyRepository.findById(cryptoCurrencyId);
        if(optionalCryptoCurrency.isEmpty()){
            return null;
        }
        CryptoCurrency cryptoCurrency = optionalCryptoCurrency.get();
        Set<Market> changes = cryptoCurrency.getChanges();

        return findLatestMarket(changes);
    }


}
