package org.example.productmanagment.application.service;

import org.example.productmanagment.application.port.in.command.CreateProductCommand;
import org.example.productmanagment.application.port.in.command.UpdateProductCommand;
import org.example.productmanagment.application.port.in.interafces.ProductManagement;
import org.example.productmanagment.application.port.out.CategoryRepository;
import org.example.productmanagment.application.port.out.ProductRepository;
import org.example.productmanagment.domain.entities.Category;
import org.example.productmanagment.domain.entities.Product;
import org.example.productmanagment.domain.enums.CategoryName;
import org.example.productmanagment.domain.errors.CategoryNotFoundError;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductManagementService implements ProductManagement {
    private final ProductRepository productRepository;

    public ProductManagementService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(CreateProductCommand command) {
        Double price = Double.parseDouble(command.getPrice());
        Integer stock = Integer.parseInt(command.getStockQuantity());
        var categoryName = this.validateCategoryName(command.getCategoryName());

        Product product = new Product(
                command.getName(),
                command.getDescription(),
                price,
                stock,
                Optional.ofNullable(categoryName)
                        .map(CategoryName::name)
                        .orElse(null)
        );

        productRepository.save(product);
    }

    private CategoryName validateCategoryName(String name) {
        System.out.println("ee " +name);
        try {
            return CategoryName.valueOf(name.toUpperCase());
        } catch (Exception e) {
            throw new CategoryNotFoundError();
        }
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
