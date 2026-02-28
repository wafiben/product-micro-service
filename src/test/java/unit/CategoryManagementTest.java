package unit;

import org.example.productmanagment.Infrastructure.category.InMemoryCategoryRepository;
import org.example.productmanagment.application.port.in.interafces.CategoryManagement;
import org.example.productmanagment.application.port.in.query.GetCategoryQuery;
import org.example.productmanagment.application.service.CategoryManagementService;
import org.example.productmanagment.domain.entities.Category;
import org.example.productmanagment.domain.errors.CategoryNotFoundError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CategoryManagementTest {
    private CategoryManagement categoryManagement;
    private InMemoryCategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        this.categoryRepository = new InMemoryCategoryRepository();
        this.categoryManagement = new CategoryManagementService(categoryRepository);
    }

    @Test
    void shouldSaveAndFindCategory() {
        // Arrange
        Category category = new Category(
                1L,
                "ELECTRONICS",
                "Tech products",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        categoryRepository.save(category);
        var cat = categoryManagement.getCategoryById(category.getId().toString());

        // Assert
        assertEquals("ELECTRONICS", cat.getName());
    }

    @Test
    void shouldThrowAnErrorWhenNotFound() {
        // Arrange
        Category category = new Category(
                1L,
                "ELECTRONICS",
                "Tech products",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        categoryRepository.save(category);
        assertThrows(CategoryNotFoundError.class, () -> {
            categoryManagement.getCategoryById("2");
        });
    }

    @Test
    void shouldFetchAllCategoriesWhenNoFilter() {
        categoryRepository.save(new Category(1L, "ELECTRONICS", "Tech", LocalDateTime.now(), LocalDateTime.now()));
        categoryRepository.save(new Category(2L, "FOOD", "Food products", LocalDateTime.now(), LocalDateTime.now()));

        List<Category> result = categoryManagement.getCategories(new GetCategoryQuery(null, null));

        assertEquals(2, result.size());
    }

    @Test
    void shouldFetchCategoriesByName() {
        categoryRepository.save(new Category(1L, "ELECTRONICS", "Tech", LocalDateTime.now(), LocalDateTime.now()));
        categoryRepository.save(new Category(2L, "FOOD", "Food products", LocalDateTime.now(), LocalDateTime.now()));

        List<Category> result = categoryManagement.getCategories(new GetCategoryQuery(null, "ELECTRONICS"));

        assertEquals(1, result.size());
        assertEquals("ELECTRONICS", result.get(0).getName());
    }

    @Test
    void shouldFetchCategoriesById() {
        categoryRepository.save(new Category(1L, "ELECTRONICS", "Tech", LocalDateTime.now(), LocalDateTime.now()));
        categoryRepository.save(new Category(2L, "FOOD", "Food products", LocalDateTime.now(), LocalDateTime.now()));

        List<Category> result = categoryManagement.getCategories(new GetCategoryQuery("1", null));

        assertEquals(1, result.size());
        assertEquals("ELECTRONICS", result.get(0).getName());
    }

    @Test
    void shouldReturnEmptyWhenNoMatch() {
        categoryRepository.save(new Category(1L, "ELECTRONICS", "Tech", LocalDateTime.now(), LocalDateTime.now()));

        List<Category> result = categoryManagement.getCategories(new GetCategoryQuery(null, "UNKNOWN"));

        assertEquals(0, result.size());
    }

}
