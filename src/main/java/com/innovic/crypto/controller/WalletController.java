package com.innovic.crypto.controller;


import javax.validation.Valid;

import com.innovic.crypto.DTO.TransactionRequest;
import com.innovic.crypto.model.Transaction;
import com.innovic.crypto.service.UserService;
import com.innovic.crypto.service.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;


    // @GetMapping("/crypto")
    // public CryptoCurencyBalance getAllCryptoCurrencies(@RequestBody Long userId){

    // }
    // @GetMapping("/crypto/{id}")
    // public CryptoCurencyBalance getCryptoCurrencyBalanceById(@RequestBody
    // )
    @PostMapping(value="/addFunds")
    public ResponseEntity<Transaction> addFunds(@Valid @RequestBody TransactionRequest transactionRequest) {
        Long userId = transactionRequest.getUserId();
        Double ammount = transactionRequest.getAmmount();

        if(!userService.checkIfExistsById(userId))
            return ResponseEntity.notFound().build();

        Transaction transaction = walletService.addFunds(userId, ammount);
        if(transaction == null)
             ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        return ResponseEntity.ok(transaction);
    }

}
