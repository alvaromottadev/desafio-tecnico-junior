package com.motta.exception;

public class FuelPumpNotFoundException extends ResourceNotFoundException {
    public FuelPumpNotFoundException(String message) {
        super(message);
    }
}
