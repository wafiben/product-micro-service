package org.example.productmanagment.application.port.in.web.controllers;
import org.example.productmanagment.application.port.in.command.CreateProductCommand;
import org.example.productmanagment.application.port.in.interafces.CategoryManagement;
import org.example.productmanagment.application.port.in.interafces.ProductManagement;
import org.example.productmanagment.application.port.in.web.requests.CreateProductRequest;
import org.example.productmanagment.domain.entities.Category;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryManagement categoryService;
    private final ProductManagement productManagement;

    public CategoryController(CategoryManagement categoryService,
                              ProductManagement productManagement) {
        this.categoryService = categoryService;
        this.productManagement = productManagement;
    }

    @PostMapping
    public Category create(@RequestBody CreateProductRequest request) {

        CreateProductCommand command = new CreateProductCommand(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getStockQuantity(),
                request.getCategoryId()
        );

        return this.productManagement.createProduct(command);
    }
}
