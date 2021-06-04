package com.innovic.crypto.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


public class UserAuthenticationRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull(message = "The email parameter must be provided.")
    @Email(message = "Please provide a valid email.")
    private String email;

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
}
