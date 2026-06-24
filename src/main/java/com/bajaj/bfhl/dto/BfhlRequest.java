package com.bajaj.bfhl.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class BfhlRequest {

    @NotNull(message = "data field is required")
    private List<String> data;

    public BfhlRequest() {}

    public BfhlRequest(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
