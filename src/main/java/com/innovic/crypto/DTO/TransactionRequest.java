package com.innovic.crypto.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class TransactionRequest {

    @NotBlank
    private Long userId;

    @NotBlank
    @Positive
    private Double ammount;

    public Long getUserId() {
        return userId;
    }
    public Double getAmmount() {
        return ammount;
    }
}
