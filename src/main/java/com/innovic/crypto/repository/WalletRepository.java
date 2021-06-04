package com.innovic.crypto.repository;

import java.util.Optional;

import com.innovic.crypto.model.Wallet;

import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository<Wallet, Long> {
    Optional<Wallet> findByUserId(Long userId);
}
