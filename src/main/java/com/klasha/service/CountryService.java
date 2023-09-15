package com.klasha.service;

import com.klasha.dto.request.CurrencyConversionRequest;
import com.klasha.dto.response.ApiResponse;


public interface CountryService {
    ApiResponse getCitiesByPopulation(long numberOfCities);

    ApiResponse getCountryData(String country);

    ApiResponse getStateDetails(String country);

    ApiResponse convertCurrency(CurrencyConversionRequest request);
}
