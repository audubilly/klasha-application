package com.klasha.dto.response;

import lombok.Data;

@Data
public class CapitalDetailResponse {
    private boolean error;
    private int status;
    private String msg;
    private CapitalDetail data;
}
