package com.contineo.inventory.validation;

public class InvalidProductException extends Exception {
    public InvalidProductException(String detail) {
        super(detail);
    }
}
