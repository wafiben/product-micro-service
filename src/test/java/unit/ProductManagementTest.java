package unit;

import org.example.productmanagment.Infrastructure.product.InMemoryProductRepository;
import org.example.productmanagment.application.port.in.command.CreateProductCommand;
import org.example.productmanagment.application.service.ProductManagementService;
import org.example.productmanagment.application.service.validators.ProductValidator;
import org.example.productmanagment.domain.entities.Category;
import org.example.productmanagment.domain.entities.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ProductManagementTest {

    private InMemoryProductRepository productRepository;

    private ProductManagementService productService;

    private ProductValidator validator;

    @BeforeEach
    void setUp() {
        validator = new ProductValidator();
        productRepository = new InMemoryProductRepository();
        productService = new ProductManagementService(productRepository, validator);
    }

    @AfterEach
    void TearDown() {
        productRepository.DeleteAll();
    }

    @Test
    void shouldSaveAndFindProduct() {
        // Arrange
        Category category = new Category(
                1L,
                "ELECTRONICS",
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
                "ELECTRONICS"
        );

        // Act
        productService.createProduct(command);
        var products = productRepository.findAll();

        // Assert
        assertEquals(1, products.size());

        Product saved = products.get(0);
        assertEquals("Laptop", saved.getName());
        assertEquals(0, new BigDecimal("1200").compareTo(saved.getPrice()));
        assertEquals(10, saved.getStockQuantity());
    }
}