package com.innovic.crypto.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.innovic.crypto.model.Market;
import com.innovic.crypto.repository.MarketRepository;


@RestController
@RequestMapping("/market")
public class MarketController {


    @Autowired
    private MarketRepository marketRepository;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Market>> getMarketPrices(){
        return ResponseEntity.ok(marketRepository.findAll());
    }


}
