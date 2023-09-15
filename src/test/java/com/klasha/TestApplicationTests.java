package com.klasha;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klasha.constants.CurrencyConstant;
import com.klasha.controller.CountryController;
import com.klasha.dto.request.CurrencyConversionRequest;
import com.klasha.dto.response.*;
import com.klasha.service.CountryService;
import com.klasha.service.impl.CountryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.klasha.constants.CurrencyConstant.EUR;
import static com.klasha.constants.CurrencyConstant.NGN;
import static com.klasha.constants.CurrencyConversionConstant.EUR_NGN;
import static com.klasha.constants.CurrencyConversionConstant.NGN_EUR;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import reactor.core.publisher.Mono;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@WebMvcTest(CountryController.class)
class TestApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CountryService service;

    @InjectMocks
    private CountryServiceImpl countryService;

    @Mock
    private WebClient.Builder webClientBuilder;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private WebClient webClient;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCitiesByPopulation() throws Exception {
        // Create a list of Population objects for testing
        List<Population> testPopulations = new ArrayList<>();

        // Create Population objects and add them to the list
        Population population1 = new Population();
        population1.setCity("Rome");
        population1.setCountry("Italy");
        population1.setCode("IT");
        List<PopulationCount> populationCount1 = new ArrayList<>();
        PopulationCount populationCount = new PopulationCount();
        populationCount.setYear("1991");
        populationCount.setValue("2860000");
        populationCount.setSex("Both Sexes");
        populationCount.setReliability("Final figure, complete");
        populationCount1.add(populationCount);
        population1.setPopulationCounts(populationCount1);
        // Set other properties for population1
        testPopulations.add(population1);

        Population population2 = new Population();
        population2.setCity("Auckland");
        population2.setCountry("New Zealand");
        population1.setCode("NZ");
        List<PopulationCount> populationCount2 = new ArrayList<>();
        PopulationCount populationCountNew = new PopulationCount();
        populationCountNew.setYear("1991");
        populationCountNew.setValue("3900000");
        populationCountNew.setSex("Both Sexes");
        populationCountNew.setReliability("Final figure, complete");
        populationCount2.add(populationCountNew);
        population2.setPopulationCounts(populationCount2);
        // Set other properties for population2
        testPopulations.add(population2);

        // Add more Population objects as needed

        // Mock the service response
        when(service.getCitiesByPopulation(anyLong())).thenReturn(
                ApiResponse.builder()
                        .msg("Successful")
                        .data(testPopulations)
                        .status(HttpStatus.OK.value())
                        .build()
        );

        // Perform the GET request and assertions
        mockMvc.perform(get("http://localhost:8080/countries/get-city-by-population")
                        .param("number_of_cities", "2")) // Adjust the number of cities as needed
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.msg").value("Successful"))
                .andExpect(jsonPath("$.data").isArray()) // Adjust the size as needed
                .andExpect(jsonPath("$.data.length()").value(2)); // Adjust the size as needed
    }


    @Test
    public void testGetCountryData() throws Exception {
        // Create a list of Population objects for testing
        List<Population> testPopulations = new ArrayList<>();

        Population population1 = new Population();
        population1.setCity("Rome");
        population1.setCountry("Italy");
        population1.setCode("IT");
        List<PopulationCount> populationCount1 = new ArrayList<>();
        PopulationCount populationCount = new PopulationCount();
        populationCount.setYear("1991");
        populationCount.setValue("2860000");
        populationCount.setSex("Both Sexes");
        populationCount.setReliability("Final figure, complete");
        populationCount1.add(populationCount);
        population1.setPopulationCounts(populationCount1);

        // Create a mock CountryData object
        CountryData countryData = new CountryData();
        countryData.setCurrency("EUR");
        countryData.setPopulationData(testPopulations);

        // Mock the service response
        when(service.getCountryData(any())).thenReturn(
                ApiResponse.builder()
                        .msg("Successful")
                        .data(countryData)
                        .status(HttpStatus.OK.value())
                        .build()
        );

        // Perform the GET request and assertions
        mockMvc.perform(get("http://localhost:8080/countries/get-country-data")
                        .param("country", "italy")) // Adjust the country as needed
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.msg").value("Successful"))
                .andExpect(jsonPath("$.data.currency").value("EUR"))
                .andExpect(jsonPath("$.data.populationData").isArray());

    }


    @Test
    public void testGetStateDetails() throws Exception {

        // Mock the service response
        when(service.getStateDetails(any())).thenReturn(
                ApiResponse.builder()
                        .msg("Successful")
                        .status(HttpStatus.OK.value())
                        .build()
        );

        // Perform the GET request and assertions
        mockMvc.perform(get("http://localhost:8080/countries/retrieve-state-detail")
                        .param("country", "nigeria")) // Adjust the country as needed
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.msg").value("Successful"))
//                .andExpect(jsonPath("$.data.name").value("nigeria"))
                .andExpect(jsonPath("$.data.states[0].name").value("Abia")); // Adjust the response structure as needed
    }


    @Test
    public void testConvertCurrency() throws Exception {

        // Mock the service response
        when(service.convertCurrency(any())).thenReturn(
                ApiResponse.builder()
                        .msg("Successful")
                        .status(HttpStatus.OK.value())
                        .build()
        );
        // Perform the POST request and assertions
        mockMvc.perform(post("http://localhost:8080/countries/currency-conversion")
                        .param("country", "italy")
                        .param("amount", String.valueOf(20))
                        .param("target-currency", String.valueOf(NGN)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.msg").value("Successful"))
                .andExpect(jsonPath("$.data.countryCurrency").value("EUR"))
                .andExpect(jsonPath("$.data.targetAmount").value("NGN 9861.2")); // Adjust the response structure as needed
    }
}




