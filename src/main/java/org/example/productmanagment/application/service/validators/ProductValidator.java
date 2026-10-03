package org.example.productmanagment.application.service.validators;

import org.example.productmanagment.domain.errors.EmptyPriceError;
import org.example.productmanagment.domain.errors.NegativePriceError;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class ProductValidator {

    public ProductValidator() {

    }

    public void validatePrice(String price) {
        if (price == null || price.isBlank()) {
            throw new EmptyPriceError();
        }
        BigDecimal priceParsed = new BigDecimal(price);

        if (priceParsed.signum() <= 0) {
            throw new NegativePriceError();
        }
    }

}
