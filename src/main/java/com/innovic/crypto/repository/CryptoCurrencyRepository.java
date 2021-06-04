package com.innovic.crypto.repository;

import java.util.Optional;

import com.innovic.crypto.model.CryptoCurrency;

import org.springframework.data.repository.CrudRepository;

public interface CryptoCurrencyRepository extends CrudRepository<CryptoCurrency, Long> {
    Optional<CryptoCurrency> findOneByExternalId(Long externalId);
    boolean existsById(Long id);
}
