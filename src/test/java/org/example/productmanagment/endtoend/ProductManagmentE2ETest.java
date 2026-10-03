package org.example.productmanagment.endtoend;

import org.example.productmanagment.application.Endpoints;
import org.example.productmanagment.application.port.in.web.requests.product.CreateProductRequest;
import org.example.productmanagment.application.port.out.CategoryRepository;
import org.example.productmanagment.application.port.out.ProductRepository;
import org.example.productmanagment.domain.entities.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ProductManagmentE2ETest extends AbstractIntegrationTest {

    private RestTemplate restTemplate;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    String url = baseUrl();

    @BeforeEach
    void setUp() {
        restTemplate = new RestTemplate();

        url = baseUrl() + Endpoints.PRODUCT;
    }


    @AfterEach
    void tearDown() {
        categoryRepository.deleteAll();
    }

    @Test
    void shouldCreateProduct() {
        // ARRANGE
        categoryRepository.save(new Category(
                null,
                "ELECTRONICS",
                "Tech products",
                LocalDateTime.now(),
                LocalDateTime.now()
        ));

        CreateProductRequest request = new CreateProductRequest(
                "Laptop",
                "Gaming laptop",
                "1200",
                "10",
                "ELECTRONICS"
        );

        // ACT
        ResponseEntity<String> response = restTemplate.postForEntity(
                url, request, String.class);

        // ASSERT
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void shouldReturnNotFoundWhenCategoryDoesNotExist() {
        CreateProductRequest request = new CreateProductRequest(
                "Laptop", "Gaming laptop", "1200", "10", "UNKNOWN");

        HttpClientErrorException exception = assertThrows(
                HttpClientErrorException.class,
                () -> restTemplate.postForEntity(url, request, String.class));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertTrue(exception.getResponseBodyAsString().contains("CATEGORY_NOT_FOUND"));
    }

    @Test
    void shouldReturnBadRequestWhenPriceIsEmpty() {
        CreateProductRequest request = new CreateProductRequest(
                "Laptop", "Gaming laptop", "", "10", "UNKNOWN");

        HttpClientErrorException exception = assertThrows(
                HttpClientErrorException.class,
                () -> restTemplate.postForEntity(url, request, String.class));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertTrue(exception.getResponseBodyAsString().contains("EMPTY_PRICE"));
    }

    @Test
    void shouldReturnBadRequestWhenPriceIsNegative() {
        CreateProductRequest request = new CreateProductRequest(
                "Laptop", "Gaming laptop", "-6199", "10", "UNKNOWN");

        HttpClientErrorException exception = assertThrows(
                HttpClientErrorException.class,
                () -> restTemplate.postForEntity(url, request, String.class));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertTrue(exception.getResponseBodyAsString().contains("PRICE_NEGATIVE")
        );
    }

}