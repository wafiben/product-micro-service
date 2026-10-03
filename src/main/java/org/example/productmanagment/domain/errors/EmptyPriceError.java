package org.example.productmanagment.domain.errors;

import org.springframework.http.HttpStatus;

public class EmptyPriceError extends BaseError {
    public EmptyPriceError() {
        super("EMPTY_PRICE", HttpStatus.BAD_REQUEST);
    }
}