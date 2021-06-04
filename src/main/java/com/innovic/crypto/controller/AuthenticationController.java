package com.innovic.crypto.controller;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.innovic.crypto.DTO.UserAuthenticationRequest;
import com.innovic.crypto.model.User;
import com.innovic.crypto.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    @Autowired
    private UserService userService;

    @PostMapping(value = "/registration")
    public ResponseEntity<User> registration(@Valid @RequestBody UserAuthenticationRequest userAuthenticationRequest){
        String username = userAuthenticationRequest.getUsername();
        String email = userAuthenticationRequest.getEmail();
        String rawPassword = userAuthenticationRequest.getPassword();

        if(userService.checkIfExistsByUsernameOrEmail(username, email)){
            return ResponseEntity.badRequest().build();
        }
        User user = userService.createUser(username, email, rawPassword);
        return ResponseEntity.ok(user);
    }

}