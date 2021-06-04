package com.innovic.crypto.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


import com.innovic.crypto.model.User;
import com.innovic.crypto.model.Wallet;
import com.innovic.crypto.service.AuthService;
import com.innovic.crypto.service.WalletService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private WalletService walletService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<User>> getAllUsers(){
        return ResponseEntity.ok(authService.getAllUsers());
    }

    @GetMapping("/{id}/wallet")
    public ResponseEntity<Wallet> getWalletByUserId(Long id) {
        Wallet wallet = walletService.getByUserId(id);
        if(wallet == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(wallet);

    }


}