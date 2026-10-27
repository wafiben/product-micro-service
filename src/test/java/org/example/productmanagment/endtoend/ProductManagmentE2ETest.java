package org.example.productmanagment.endtoend;

import org.example.productmanagment.application.Endpoints;
import org.example.productmanagment.application.port.in.web.requests.product.CreateProductRequest;
import org.example.productmanagment.application.port.out.CategoryRepository;
import org.example.productmanagment.application.port.out.ProductRepository;
import org.example.productmanagment.domain.entities.Category;
import org.example.productmanagment.domain.entities.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
        productRepository.deleteAll();
    }

    @Test
    void shouldCreateAndGetProducts() {
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
                url, request, String.class
        );

        // ASSERT POST
        assertEquals(201, response.getStatusCode().value());

        // ASSERT DATABASE
        List<Product> products = productRepository.findAll();

        assertEquals(1, products.size());
        assertEquals("Laptop", products.get(0).getName());
        assertEquals("Gaming laptop", products.get(0).getDescription());
        assertEquals(0, new BigDecimal("1200").compareTo(products.get(0).getPrice()));
        assertEquals(10, products.get(0).getStockQuantity());
        assertEquals("ELECTRONICS", products.get(0).getCategory());
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