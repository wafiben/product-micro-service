package org.example.productmanagment.application.port.in.interafces;

import org.example.productmanagment.application.port.in.command.CreateProductCommand;
import org.example.productmanagment.application.port.in.command.UpdateProductCommand;
import org.example.productmanagment.domain.entities.Product;

import java.util.List;

public interface ProductManagement {
    void createProduct(CreateProductCommand command);

    Product updateProduct(Long id, UpdateProductCommand command);

    void deleteProduct(Long id);

    Product getProductById(Long id);

    List<Product> getAllProducts();
}
