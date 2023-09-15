package com.klasha.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class StateDetail {

    private String name;
    private List<State> states;
}
