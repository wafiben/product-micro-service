package org.example.productmanagment;

import org.example.productmanagment.application.port.in.web.requests.CreateProductRequest;
import org.example.productmanagment.endtoend.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagementIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateCategoryAndProduct() {
        CreateProductRequest productRequest = new CreateProductRequest(
                "Laptop",
                "Gaming laptop",
                "1500.00",
                "10",
                "ELECTRONICS"
        );

        var productResponse = restTemplate.postForEntity(
                "/api/products",
                productRequest,
                Void.class
        );

        //Then: Product is created successfully
        assertEquals(HttpStatus.CREATED, productResponse.getStatusCode());
        assertNotNull(productResponse.getBody());
    }
}


