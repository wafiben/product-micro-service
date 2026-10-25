package org.example.productmanagment.application.port.in.web.controllers;

import org.example.productmanagment.application.Endpoints;
import org.example.productmanagment.application.port.in.command.CreateProductCommand;
import org.example.productmanagment.application.port.in.interafces.ProductManagement;
import org.example.productmanagment.application.port.in.web.requests.product.CreateProductRequest;
import org.example.productmanagment.domain.entities.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(Endpoints.PRODUCT)
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return this.productManagement.getAllProducts();
    }
}