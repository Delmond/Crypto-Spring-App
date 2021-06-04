package com.innovic.crypto.repository;

import com.innovic.crypto.model.Market;

import org.springframework.data.repository.CrudRepository;

public interface MarketRepository extends CrudRepository<Market, Long> {
    public Iterable<Market> findAllByCryptoCurrencyId(Long id);
}
