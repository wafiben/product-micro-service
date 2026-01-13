package org.example.productmanagment.domain.errors;

import org.springframework.http.HttpStatus;


public class BaseError extends RuntimeException {
    private final String errorCode;
    private final HttpStatus status;

    public BaseError(String errorCode, HttpStatus status) {
        super(errorCode);
        this.errorCode = errorCode;
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
