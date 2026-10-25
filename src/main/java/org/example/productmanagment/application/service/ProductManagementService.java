package org.example.productmanagment.application.service;
import org.example.productmanagment.application.port.in.command.CreateProductCommand;
import org.example.productmanagment.application.port.in.command.UpdateProductCommand;
import org.example.productmanagment.application.port.in.interafces.ProductManagement;
import org.example.productmanagment.application.port.out.ProductRepository;
import org.example.productmanagment.application.service.validators.ProductValidator;
import org.example.productmanagment.domain.entities.Product;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductManagementService implements ProductManagement {

    private ProductValidator validator;

    private final ProductRepository productRepository;


    public ProductManagementService(ProductRepository productRepository,
                                    ProductValidator validator) {

        this.productRepository = productRepository;

        this.validator = validator;
    }

    public void createProduct(CreateProductCommand command) {
        this.validator.validatePrice(command.getPrice());

        BigDecimal price = new BigDecimal(command.getPrice());

        Integer stock = Integer.parseInt(command.getStockQuantity());

        Product product = new Product(
                command.getName(),
                command.getDescription(),
                price,
                stock,
                command.getCategoryName()
        );

        productRepository.save(product);
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
