package org.example.productmanagment.endtoend;

import org.example.productmanagment.application.port.in.web.requests.category.CreateCategoryRequest;
import org.example.productmanagment.application.port.in.web.response.category.CategoryDto;
import org.example.productmanagment.application.port.out.CategoryRepository;
import org.example.productmanagment.domain.entities.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CategoryManagementE2ETest extends AbstractIntegrationTest {

    private RestTemplate restTemplate;

    @Autowired
    private CategoryRepository categoryRepository;

    String url = baseUrl() + "/categories";

    @BeforeEach
    void setUp() throws SQLException {
        restTemplate = new RestTemplate();

        url = baseUrl() + "/categories";
    }

    @AfterEach
    void tearDown() {
        categoryRepository.deleteAll();
    }

    @Test
    void shouldCreateCategory() {

        CreateCategoryRequest request = new CreateCategoryRequest("ELECTRONICS", "Test Description");
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        assertEquals(201, response.getStatusCode().value());

        List<Category> categories = categoryRepository.findAll();
        assertEquals(1, categories.size());
        assertEquals("ELECTRONICS", categories.get(0).getName());
    }

    @Test
    void shouldDeleteCategory() {

        restTemplate.postForEntity(url, new CreateCategoryRequest("ELECTRONICS", "Tech"), CategoryDto.class);

        String id = categoryRepository.findAll().get(0).getId().toString();

        restTemplate.delete(url + "/" + id);

        assertEquals(0, categoryRepository.findAll().size());
    }
}