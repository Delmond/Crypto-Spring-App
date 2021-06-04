package com.innovic.crypto.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class CryptoCurrency {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private Long externalId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String symbol;

    @OneToMany(mappedBy = "cryptoCurrency")
    private Set<Market> changes;

    public CryptoCurrency(){}

    public CryptoCurrency(Long externalId, String name, String symbol){
        this.externalId = externalId;
        this.name = name;
        this.symbol = symbol;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSymbol() {
        return symbol;
    }
    public Long getExternalId() {
        return externalId;
    }
    @JsonIgnore
    public Set<Market> getChanges() {
        return changes;
    }
}
