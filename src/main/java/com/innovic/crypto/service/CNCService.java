package com.innovic.crypto.service;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import com.innovic.crypto.DTO.CNC.ApiResult;
import com.innovic.crypto.DTO.CNC.CurrencyInfo;
import com.innovic.crypto.model.CryptoCurrency;
import com.innovic.crypto.model.Market;
import com.innovic.crypto.repository.CryptoCurrencyRepository;
import com.innovic.crypto.repository.MarketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CNCService implements IMarketUpdaterService{
    private static String API_KEY_HEADER = "X-CMC_PRO_API_KEY";
    private static String API_KEY = "Please provide a valid API key";
    private static String URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    @Autowired
    private MarketRepository marketRepository;

    private ApiResult fetchFromAPI() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(API_KEY_HEADER, API_KEY);

        HttpEntity<String> entity = new HttpEntity<>("", headers);

        ResponseEntity<ApiResult> response = restTemplate.exchange(URL, HttpMethod.GET, entity, ApiResult.class);
        if(response.getStatusCode() != HttpStatus.OK)
            return null;
        return response.getBody();
    }

    private CryptoCurrency createFromDTO(CurrencyInfo currencyInfo){
        CryptoCurrency cryptoCurrency = new CryptoCurrency(currencyInfo.getId(), currencyInfo.getName(), currencyInfo.getSymbol());
        return cryptoCurrencyRepository.save(cryptoCurrency);
    }
    private CryptoCurrency createIfNotExists(CurrencyInfo currency){

        Long externalId = currency.getId();
        Optional<CryptoCurrency> possibleCryptoCurrency = cryptoCurrencyRepository.findOneByExternalId(externalId);
        if(!possibleCryptoCurrency.isPresent()){
            return createFromDTO(currency);
        } else {
            return possibleCryptoCurrency.get();
        }

    }

    public void update(){
        ApiResult result = fetchFromAPI();
        if(result == null)
            return;

        Instant timestamp = result.getStatus().getTimestamp();
        Set<CurrencyInfo> fetchedCurrencies = result.getData();

        for (CurrencyInfo currencyInfo : fetchedCurrencies) {
            CryptoCurrency currency = createIfNotExists(currencyInfo);
            Market market = new Market(timestamp, currency, currencyInfo.getQuote().getUsd().getPrice());
            marketRepository.save(market);
        }

    }

}
