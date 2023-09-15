package com.klasha.dto.response;

import lombok.Data;

@Data
public class PopulationCount{
    private String year;
    private String value;
    private String sex;
    private String reliability;
}
