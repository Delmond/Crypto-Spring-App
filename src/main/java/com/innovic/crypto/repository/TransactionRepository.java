package com.innovic.crypto.repository;

import org.springframework.data.repository.CrudRepository;

import com.innovic.crypto.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
