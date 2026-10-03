package org.example.productmanagment.application.port.in.web;


import org.example.productmanagment.domain.errors.BaseError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseError.class)
    public ResponseEntity<Map<String, String>> handleBaseError(BaseError ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(Map.of("error", ex.getErrorCode()));
    }
}
