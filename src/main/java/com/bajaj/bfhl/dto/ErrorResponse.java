package com.bajaj.bfhl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {

    @JsonProperty("is_success")
    private boolean isSuccess;

    @JsonProperty("message")
    private String message;

    public ErrorResponse(String message) {
        this.isSuccess = false;
        this.message = message;
    }

    public boolean isSuccess() { return isSuccess; }
    public String getMessage() { return message; }
}
