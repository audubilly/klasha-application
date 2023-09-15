package com.klasha.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class CountryDto implements Serializable {
    private String country;
    private String state;
}
