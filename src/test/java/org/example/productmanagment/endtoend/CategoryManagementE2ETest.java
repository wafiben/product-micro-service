package org.example.productmanagment.endtoend;

import org.example.productmanagment.application.port.in.web.requests.category.CreateCategoryRequest;
import org.example.productmanagment.application.port.in.web.response.category.CategoryDto;
import org.example.productmanagment.application.port.out.CategoryRepository;
import org.example.productmanagment.domain.entities.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CategoryManagementE2ETest extends AbstractIntegrationTest {

    @LocalServerPort
    private int port;

    private RestTemplate restTemplate;

    String url = AbstractIntegrationTest.BASE_URL + "/categories";

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        restTemplate = new RestTemplate();
    }

    @AfterEach
    void tearDown() {
        categoryRepository.deleteAll();
    }

    @Test
    void shouldCreateCategory() {
        String url = "http://localhost:" + port + "/categories";
        CreateCategoryRequest request = new CreateCategoryRequest("ELECTRONICS", "Test Description");
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        assertEquals(201, response.getStatusCode().value());

        List<Category> categories = categoryRepository.findAll();
        assertEquals(1, categories.size());
        assertEquals("ELECTRONICS", categories.get(0).getName());
    }

    @Test
    void shouldDeleteCategory() {
        String baseUrl = "http://localhost:" + port + "/categories";

        restTemplate.postForEntity(baseUrl, new CreateCategoryRequest("ELECTRONICS", "Tech"), CategoryDto.class);

        String id = categoryRepository.findAll().get(0).getId().toString();

        restTemplate.delete(baseUrl + "/" + id);

        assertEquals(0, categoryRepository.findAll().size());
    }
}