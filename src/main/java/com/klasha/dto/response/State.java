package com.klasha.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class State {
    private String name;

    @JsonAlias("state_code")
    private String stateCode;
    private List<String> cities;
}
