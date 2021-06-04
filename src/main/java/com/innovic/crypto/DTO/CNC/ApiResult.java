package com.innovic.crypto.DTO.CNC;

import java.util.ArrayList;
import java.util.Set;

public class ApiResult {
    private Status status;
    private Set<CurrencyInfo> data;


    public Status getStatus() {
        return status;
    }
    public Set<CurrencyInfo> getData() {
        return data;
    }
}
