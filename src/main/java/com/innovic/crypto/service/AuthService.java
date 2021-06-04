package com.innovic.crypto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovic.crypto.repository.UserRepository;
import com.innovic.crypto.repository.WalletRepository;
import com.innovic.crypto.model.User;
import com.innovic.crypto.model.Wallet;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User createUser(User user){

        User createdUser = userRepository.save(user);

        // Create a wallet for the user
        Wallet wallet = new Wallet(createdUser);
        walletRepository.save(wallet);
        createdUser.setWallet(wallet);

        return createdUser;
    }

}
