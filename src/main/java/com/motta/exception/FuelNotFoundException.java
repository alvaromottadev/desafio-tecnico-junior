package com.motta.exception;

public class FuelNotFoundException extends RuntimeException {
    public FuelNotFoundException(String message) {
        super(message);
    }
}
