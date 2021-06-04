package com.innovic.crypto.service;

import java.util.Optional;

import com.innovic.crypto.model.CryptoCurrency;
import com.innovic.crypto.model.Market;
import com.innovic.crypto.repository.CryptoCurrencyRepository;
import com.innovic.crypto.repository.MarketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CryptoCurrencyService {
    @Autowired
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    @Autowired
    private MarketRepository marketRepository;

    public Iterable<CryptoCurrency> getAll(){
        return cryptoCurrencyRepository.findAll();
    }
    public CryptoCurrency getById(Long id){
        Optional<CryptoCurrency> cryptoCurrency = cryptoCurrencyRepository.findById(id);
        return cryptoCurrency.orElse(null);
    }
    public Iterable<Market> getAllChangesById(Long id){
        if(!cryptoCurrencyRepository.existsById(id))
            return null;

        return marketRepository.findAllByCryptoCurrencyId(id);
    }

}
