package com.klasha.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private boolean error;
    private int status;
    private String msg;
    private Object data;

    public ApiResponse(int status, String message) {
        this.status = status;
        this.msg = message;
        this.setError(true);
    }
}
