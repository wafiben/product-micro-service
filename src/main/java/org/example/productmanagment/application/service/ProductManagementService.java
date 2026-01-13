package org.example.productmanagment.application.service;

import org.example.productmanagment.application.port.in.command.CreateProductCommand;
import org.example.productmanagment.application.port.in.command.UpdateProductCommand;
import org.example.productmanagment.application.port.in.interafces.ProductManagement;
import org.example.productmanagment.application.port.out.CategoryRepository;
import org.example.productmanagment.application.port.out.ProductRepository;
import org.example.productmanagment.domain.entities.Category;
import org.example.productmanagment.domain.entities.Product;
import org.example.productmanagment.domain.errors.CategoryNotFoundError;

import java.util.List;

public class ProductManagementService implements ProductManagement {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductManagementService(ProductRepository productRepository,
                                    CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(CreateProductCommand command) {

        Double price = Double.parseDouble(command.getPrice());
        Integer stock = Integer.parseInt(command.getStockQuantity());
        Long categoryId = Long.parseLong(command.getCategoryId());

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(CategoryNotFoundError::new).getCategory();

        Product product = new Product(
                command.getName(),
                command.getDescription(),
                price,
                stock,
                category
        );

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, UpdateProductCommand command) {
        // TODO: Implement update logic
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        // TODO: Implement delete logic
    }

    @Override
    public Product getProductById(Long id) {
        // TODO: Implement get by ID logic
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        // TODO: Implement get all products logic
        return null;
    }
}
