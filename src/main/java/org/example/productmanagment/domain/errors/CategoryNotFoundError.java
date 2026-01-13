package org.example.productmanagment.domain.errors;

import org.springframework.http.HttpStatus;

public class CategoryNotFoundError extends BaseError {

    public CategoryNotFoundError() {
        super("CATEGORY_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}