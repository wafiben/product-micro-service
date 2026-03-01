package org.example.productmanagment.Infrastructure.category;

import org.example.productmanagment.application.port.in.query.GetCategoryQuery;
import org.example.productmanagment.application.port.out.CategoryRepository;
import org.example.productmanagment.domain.entities.Category;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryCategoryRepository implements CategoryRepository {

    private List<Category> categories = new ArrayList<>();


    public void save(Category category) {
        Category categoryObject = new Category(category.getId(), category.getName(), category.getDescription(),
                LocalDateTime.now(), LocalDateTime.now());
        categories.add(categoryObject);
    }


    public Optional<Category> findById(String id) {
        return this.categories.stream()
                .filter(elt -> elt.getId().toString().equals(id))
                .findFirst();
    }

    public List<Category> findAll() {
        return List.of();
    }


    public void deleteById(String id) {
        categories.removeIf(category -> category.getId().toString().equals(id));
    }

    public List<Category> fetchCategories(GetCategoryQuery query) {
        return categories.stream()
                .filter(e -> query.getId() == null || e.getId().toString().equals(query.getId()))
                .filter(e -> query.getName() == null || e.getName().equals(query.getName()))
                .map(e -> new Category(
                        e.getId(),
                        e.getName(),
                        e.getDescription(),
                        e.getCreatedAt(),
                        e.getUpdatedAt()
                ))
                .toList();
    }

    @Override
    public void deleteAll() {

    }

}
