package com.motta.exception;

import com.motta.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(404).body(new ErrorResponse("Resource not found"));
    }

    @ExceptionHandler(FuelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleFuelNotFoundException(FuelNotFoundException ex) {
        return ResponseEntity.status(404).body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(FuelPumpNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleFuelPumpNotFoundException(FuelPumpNotFoundException ex) {
        return ResponseEntity.status(404).body(new ErrorResponse(ex.getMessage()));
    }


}
