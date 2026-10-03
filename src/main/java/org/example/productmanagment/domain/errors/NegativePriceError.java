package org.example.productmanagment.domain.errors;

import org.springframework.http.HttpStatus;

public class NegativePriceError extends BaseError {
    public NegativePriceError() {
        super("PRICE_NEGATIVE", HttpStatus.BAD_REQUEST);
    }
}