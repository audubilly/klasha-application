package com.klasha.controller;

import com.klasha.constants.ApiRoute;
import com.klasha.constants.CurrencyConstant;
import com.klasha.dto.request.CurrencyConversionRequest;
import com.klasha.dto.response.ApiResponse;
import com.klasha.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService service;

    @GetMapping(ApiRoute.GET_CITY_BY_POPULATION)
    public ApiResponse getCitiesByPopulation(@RequestParam("number_of_cities") Long numberOfCities){
        return service.getCitiesByPopulation(numberOfCities);
    }

    @GetMapping(ApiRoute.GET_COUNTRY_DATA)
    public ApiResponse getCountryData(@RequestParam String country){
        return service.getCountryData(country);
    }

    @GetMapping(ApiRoute.RETRIEVE_STATE_DETAIL)
    public ApiResponse retrieveStateDetails(@RequestParam String country){
        return service.getStateDetails(country);
    }

    @PostMapping(ApiRoute.CONVERT_CURRENCY)
    public ApiResponse convertCurrency(@RequestParam String country, @RequestParam double amount, @RequestParam("target-currency") CurrencyConstant targetCurrency){
        CurrencyConversionRequest request = CurrencyConversionRequest.createConversionRequest(country, amount, targetCurrency);
        return service.convertCurrency(request);
    }
}
