package com.innovic.crypto.controller;

import javax.validation.Valid;

import com.innovic.crypto.DTO.TransactionRequest;
import com.innovic.crypto.model.CryptoCurrency;
import com.innovic.crypto.model.Market;
import com.innovic.crypto.model.Transaction;
import com.innovic.crypto.model.Wallet;
import com.innovic.crypto.repository.CryptoCurrencyRepository;
import com.innovic.crypto.service.CryptoCurrencyService;
import com.innovic.crypto.service.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crypto")
public class CryptoCurrencyController {

    @Autowired
    private CryptoCurrencyService cryptoCurrencyService;

    @Autowired
    private WalletService walletService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<CryptoCurrency>> getAllCryptoCurrencies(){
        return ResponseEntity.ok(cryptoCurrencyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CryptoCurrency> getCryptoCurrencyById(@PathVariable Long id){
        CryptoCurrency cryptoCurrency = cryptoCurrencyService.getById(id);
        if(cryptoCurrency == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cryptoCurrency);
    }

    @GetMapping("/{id}/market")
    public ResponseEntity<Iterable<Market>> getChangesByCryptoCurrencyId(@PathVariable Long id){
        Iterable<Market> changes = cryptoCurrencyService.getAllChangesById(id);
        if(changes == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(changes);
    }

    @PostMapping("/{id}/buy")
    public ResponseEntity<Transaction> buy(@PathVariable Long id, @Valid @RequestBody TransactionRequest transactionRequest){
        Long userId = transactionRequest.getUserId();
        Double ammount = transactionRequest.getAmmount();

        if(!walletService.checkIfValidTransaction(userId, id)){
            return ResponseEntity.badRequest().build();
        }

        Transaction transaction = walletService.buyCryptoCurrency(userId, id, ammount);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/{id}/sell")
    public ResponseEntity<Transaction> sell(@PathVariable Long id, @Valid @RequestBody TransactionRequest transactionRequest){
        Long userId = transactionRequest.getUserId();
        Double ammount = transactionRequest.getAmmount();

        if(!walletService.checkIfValidTransaction(userId, id)){
            return ResponseEntity.badRequest().build();
        }

        Transaction transaction = walletService.sellCryptoCurrency(userId, id, ammount);
        return ResponseEntity.ok(transaction);
    }

}
