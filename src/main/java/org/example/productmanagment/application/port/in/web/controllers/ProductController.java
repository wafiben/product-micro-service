package org.example.productmanagment.application.port.in.web.controllers;

import org.example.productmanagment.application.port.in.command.CreateProductCommand;
import org.example.productmanagment.application.port.in.interafces.ProductManagement;
import org.example.productmanagment.application.port.in.web.requests.product.CreateProductRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;


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