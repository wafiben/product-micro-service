package org.example.productmanagment.application.port.in.interafces;

import org.example.productmanagment.application.port.in.command.CreateCategoryCommand;
import org.example.productmanagment.application.port.in.command.UpdateCategoryCommand;
import org.example.productmanagment.application.port.in.query.GetCategoryQuery;
import org.example.productmanagment.domain.entities.Category;

import java.util.List;

public interface CategoryManagement {
    void createCategory(CreateCategoryCommand command);

    Category updateCategory(Long id, UpdateCategoryCommand command);

    void deleteCategory(Long id);

    Category getCategoryById(String id);

    List<Category> getCategories(GetCategoryQuery query);
}