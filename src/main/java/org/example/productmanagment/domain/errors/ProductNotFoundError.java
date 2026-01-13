package org.example.productmanagment.domain.errors;

import org.springframework.http.HttpStatus;

public class ProductNotFoundError extends BaseError {

    public ProductNotFoundError(Long productId) {
        super("PRODUCT_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    public ProductNotFoundError(String message) {
        super("PRODUCT_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}