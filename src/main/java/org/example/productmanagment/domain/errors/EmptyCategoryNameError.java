package org.example.productmanagment.domain.errors;

import org.springframework.http.HttpStatus;

public class EmptyCategoryNameError extends BaseError {
    public EmptyCategoryNameError() {
        super("EMPTY_CATEGORY_NAME", HttpStatus.BAD_REQUEST);
    }
}
