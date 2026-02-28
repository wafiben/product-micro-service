package org.example.productmanagment.application.service;

import org.example.productmanagment.application.port.in.command.CreateCategoryCommand;
import org.example.productmanagment.application.port.in.command.UpdateCategoryCommand;
import org.example.productmanagment.application.port.in.interafces.CategoryManagement;
import org.example.productmanagment.application.port.in.query.GetCategoryQuery;
import org.example.productmanagment.application.port.out.CategoryRepository;
import org.example.productmanagment.domain.entities.Category;

import java.util.List;

import org.example.productmanagment.domain.errors.CategoryNotFoundError;
import org.springframework.stereotype.Service;

@Service
public class CategoryManagementService implements CategoryManagement {

    private CategoryRepository categoryRepo;

    public CategoryManagementService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public void createCategory(CreateCategoryCommand command) {
        Category category = new Category(
                command.getName(),
                command.getDescription()
        );

        categoryRepo.save(category);
    }

    @Override
    public Category updateCategory(Long id, UpdateCategoryCommand command) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }


    public Category getCategoryById(String id) {
        return categoryRepo.findById(id)
                .orElseThrow(CategoryNotFoundError::new);
    }

   
    public List<Category> getCategories(GetCategoryQuery query) {
        return this.categoryRepo.fetchCategories(query);
    }

    public List<Category> getAllCategories() {
        return List.of();
    }
}
