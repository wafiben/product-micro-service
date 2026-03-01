package org.example.productmanagment.application.port.out;

import org.example.productmanagment.application.port.in.query.GetCategoryQuery;
import org.example.productmanagment.domain.entities.Product;
import org.example.productmanagment.domain.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    void save(Category category);

    Optional<Category> findById(String id);

    List<Category> findAll();

    void deleteById(String id);

    List<Category> fetchCategories(GetCategoryQuery query);

    void deleteAll();
}
