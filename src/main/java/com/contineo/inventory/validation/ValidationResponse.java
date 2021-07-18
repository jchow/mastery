package com.contineo.inventory.validation;

public class ValidationResponse {
    private boolean result;
    private String message;

    public ValidationResponse(boolean result, String message) {

        this.result = result;
        this.message = message;
    }

    public ValidationResponse(boolean result) {
        this.result = result;
    }

    public boolean valid() {
        return result;
    }

    public String message() {
        return message;
    }
}
