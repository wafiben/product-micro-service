package org.example.productmanagment.domain.errors;

import org.springframework.http.HttpStatus;

public class ProductNotFoundError extends BaseError {

    public ProductNotFoundError() {
        super("PRODUCT_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}