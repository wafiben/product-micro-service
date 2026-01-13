package org.example.productmanagment.application.service;

import org.example.productmanagment.application.port.in.command.CreateCategoryCommand;
import org.example.productmanagment.application.port.in.command.UpdateCategoryCommand;
import org.example.productmanagment.application.port.in.interafces.CategoryManagement;
import org.example.productmanagment.domain.entities.Category;

import java.util.List;

public class CategoryManagementService implements CategoryManagement {
    @Override
    public Category createCategory(CreateCategoryCommand command) {
        return null;
    }

    @Override
    public Category updateCategory(Long id, UpdateCategoryCommand command) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }

    @Override
    public Category getCategoryById(Long id) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }
}
