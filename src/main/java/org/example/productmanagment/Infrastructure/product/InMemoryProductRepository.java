package org.example.productmanagment.Infrastructure.product;

import org.example.productmanagment.application.port.in.command.CreateProductCommand;
import org.example.productmanagment.application.port.in.command.UpdateProductCommand;
import org.example.productmanagment.application.port.in.interafces.ProductManagement;
import org.example.productmanagment.domain.entities.Category;
import org.example.productmanagment.domain.entities.Product;
import org.example.productmanagment.domain.errors.CategoryNotFoundError;

import java.util.ArrayList;
import java.util.List;

public class InMemoryProductRepository implements ProductManagement {
    private List<Product> products = new ArrayList<>();

    private final List<Category> categories = new ArrayList<>();

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public void createProduct(CreateProductCommand command) {
        // Convert values
        double price = Double.parseDouble(command.getPrice());
        int stock = Integer.parseInt(command.getStockQuantity());
        long categoryId = Long.parseLong(command.getCategoryId());

        // Find the category
        Category category = categories.stream()
                .filter(c -> c.getId() == categoryId)
                .findFirst()
                .orElseThrow(() -> new CategoryNotFoundError());
        // Create product
        Product product = new Product(
                command.getName(),
                command.getDescription(),
                price,
                stock,
                category
        );

        // Add to in-memory list
        products.add(product);
    }

    @Override
    public Product updateProduct(Long id, UpdateProductCommand command) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    public void addCategory(Category category) {
        categories.add(category);
    }
}
