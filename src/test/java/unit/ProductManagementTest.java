package unit;

import org.example.productmanagment.Infrastructure.product.InMemoryProductRepository;
import org.example.productmanagment.application.port.in.command.CreateProductCommand;
import org.example.productmanagment.domain.entities.Category;
import org.example.productmanagment.domain.entities.Product;
import org.example.productmanagment.domain.errors.CategoryNotFoundError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ProductManagementTest {

    private InMemoryProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new InMemoryProductRepository();
    }

    @Test
    void shouldSaveAndFindProduct() {
        // Arrange
        Category category = new Category(
                1L,
                "Tech",
                "Tech products",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        productRepository.addCategory(category);

        CreateProductCommand command = new CreateProductCommand(
                "Laptop",
                "Gaming laptop",
                "1200",
                "10",
                "1"
        );

        // Act
        productRepository.createProduct(command);
        var products = productRepository.findAll();

        // Assert
        assertEquals(1, products.size());

        Product saved = products.get(0);
        assertEquals("Laptop", saved.getName());
        assertEquals(1200.0, saved.getPrice());
        assertEquals(10, saved.getStockQuantity());
    }

    @Test
    void shouldThrowWhenCategoryNotFound() {
        CreateProductCommand command = new CreateProductCommand(
                "Phone",
                "Smartphone",
                "800",
                "5",
                "ssss"
        );
        System.out.println(command);

        assertThrows(CategoryNotFoundError.class, () -> {
            productRepository.createProduct(command);
        });
    }
}
