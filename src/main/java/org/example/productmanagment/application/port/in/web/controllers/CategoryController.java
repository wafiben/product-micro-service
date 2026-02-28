package org.example.productmanagment.application.port.in.web.controllers;

import org.example.productmanagment.application.port.in.command.CreateCategoryCommand;
import org.example.productmanagment.application.port.in.interafces.CategoryManagement;
import org.example.productmanagment.application.port.in.query.GetCategoryQuery;
import org.example.productmanagment.application.port.in.web.requests.category.CreateCategoryRequest;
import org.example.productmanagment.application.port.in.web.response.category.CategoryDto;
import org.example.productmanagment.domain.entities.Category;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryManagement categoryService;

    public CategoryController(CategoryManagement categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable String id) {
        Category category = this.categoryService.getCategoryById(id);

        return new CategoryDto(
                category.getId().toString(),
                category.getName()
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> getAllCategories(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String name) {

        GetCategoryQuery getCategoryQuery = new GetCategoryQuery(id, name);

        return this.categoryService.getCategories(getCategoryQuery)
                .stream()
                .map(elt -> new CategoryDto(
                        elt.getId().toString(),
                        elt.getName()
                ))
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateCategoryRequest request) {
        CreateCategoryCommand command = new CreateCategoryCommand(
                request.getName(),
                request.getDescription()
        );
        this.categoryService.createCategory(command);
    }
}