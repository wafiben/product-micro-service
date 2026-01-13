package org.example.productmanagment.domain.errors;

import org.springframework.http.HttpStatus;

public class CategoryNameTooLongError extends BaseError {

    public CategoryNameTooLongError() {
        super("CATEGORY_NAME_TOO_LONG",
                HttpStatus.BAD_REQUEST);
    }
}