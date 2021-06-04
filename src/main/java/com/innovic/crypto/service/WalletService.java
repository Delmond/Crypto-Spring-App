package com.innovic.crypto.service;

import java.util.Optional;

import com.innovic.crypto.DTO.WalletBalance;
import com.innovic.crypto.model.CryptoCurrency;
import com.innovic.crypto.model.Market;
import com.innovic.crypto.model.Transaction;
import com.innovic.crypto.model.TransactionType;
import com.innovic.crypto.model.User;
import com.innovic.crypto.model.Wallet;
import com.innovic.crypto.repository.CryptoCurrencyRepository;
import com.innovic.crypto.repository.TransactionRepository;
import com.innovic.crypto.repository.UserRepository;
import com.innovic.crypto.repository.WalletRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    @Autowired
    private MarketService marketService;

    public Wallet getByUserId(Long userId){
        Optional<Wallet> wallet = walletRepository.findByUserId(userId);
        return wallet.orElse(null);
    }

    public Transaction addFunds(Long userId, Double ammount){
        Optional<Wallet> possibleWallet = walletRepository.findByUserId(userId);
        if(possibleWallet.isEmpty())
            return null;

        Transaction transaction = new Transaction(possibleWallet.get(), null, ammount, TransactionType.ADD_FUNDS);
        return transactionRepository.save(transaction);
    }

    // public WalletBalance getWalletBalance(Long userId){
    //     Iterable<CryptoCurrency> cryptoCurrencies = cryptoCurrencyRepository.findAll();
    //     WalletBalance walletBalance = new WalletBalance();
    //     for (CryptoCurrency cryptoCurrency : cryptoCurrencies) {

    //         Market lastestChange = marketService.findLatestPrice(cryptoCurrency.getChanges());

    //     }
    //     walletBalance.
    // }
    public Transaction buyCryptoCurrency(Long userId, Long cryptoCurrencyId, Double ammount){
        Wallet wallet = walletRepository.findByUserId(userId)
                                        .orElse(null);
        Market latestChange = marketService.getLatestMarketByCryptoCurrencyId(cryptoCurrencyId);

        if(wallet == null || latestChange == null)
            return null;

        Double balance = wallet.getBalance();
        Double price = latestChange.getPrice();

        if(balance < ammount*price)
            return null;
        Transaction transaction = new Transaction(wallet, latestChange, ammount, TransactionType.BUY);
        return transactionRepository.save(transaction);
    }


    public Transaction sellCryptoCurrency(Long userId, Long cryptoCurrencyId, Double ammount){
        Wallet wallet = walletRepository.findByUserId(userId).orElse(null);
        Market latestChange = marketService.getLatestMarketByCryptoCurrencyId(cryptoCurrencyId);
        if(wallet == null || latestChange == null)
            return null;

        //We need to check how much of the cryptoCurrency we have


        Double cryptoCurrecnyAmmount = wallet.getTransactions()
                                             .stream()
                                             .filter(transaction -> transaction.getTransactionType() != TransactionType.ADD_FUNDS) // Remove transactions which were adding funds
                                             .filter(transaction -> transaction.getMarket().getCryptoCurrency().getId() == cryptoCurrencyId) // Remove transaction which aren't for the requested cryptocurrency
                                             .map(transaction -> (transaction.getTransactionType() == TransactionType.BUY)? transaction.getAmmount(): -transaction.getAmmount()) // If the currency was bough, add the ammount, if it was sold, substract it
                                             .reduce(0.0, Double::sum); // Sum the remating values

        if(cryptoCurrecnyAmmount < ammount){
            return null;
        }
        Transaction transaction = new Transaction(wallet, latestChange, ammount, TransactionType.SELL);

        return transactionRepository.save(transaction);
    }

    public boolean checkIfValidTransaction(Long userId, Long cryptoCurrencyId){
        return userRepository.existsById(userId) && cryptoCurrencyRepository.existsById(cryptoCurrencyId);
    }
}
