package com.klasha.dto.response;

import lombok.Data;

@Data
public class CurrencyConversionResponse {
    private String countryCurrency;
    private String targetAmount;
}
