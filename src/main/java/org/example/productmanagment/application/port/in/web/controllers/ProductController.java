package org.example.productmanagment.application.port.in.web.controllers;

import org.example.productmanagment.application.port.in.command.CreateProductCommand;
import org.example.productmanagment.application.port.in.interafces.CategoryManagement;
import org.example.productmanagment.application.port.in.interafces.ProductManagement;
import org.example.productmanagment.application.port.in.web.requests.CreateProductRequest;
import org.example.productmanagment.domain.entities.Category;
import org.example.productmanagment.domain.entities.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductManagement productManagement;

    public ProductController(ProductManagement productManagement) {
        this.productManagement = productManagement;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateProductRequest request) {

        var command = new CreateProductCommand(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getStockQuantity(),
                request.getCategoryName()
        );
        this.productManagement.createProduct(command);
    }
}