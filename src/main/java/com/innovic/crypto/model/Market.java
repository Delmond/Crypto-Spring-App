package com.innovic.crypto.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Market {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Instant timestamp;

    @ManyToOne
    private CryptoCurrency cryptoCurrency;

    @Column(nullable = false)
    private Double price;

    public Market(){}

    public Market(Instant timestamp, CryptoCurrency cryptoCurrency, Double price){
        this.timestamp = timestamp;
        this.cryptoCurrency = cryptoCurrency;
        this.price = price;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
    @JsonIgnore
    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }
    public Double getPrice() {
        return price;
    }

}
