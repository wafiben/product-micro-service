package org.example.productmanagment.Infrastructure.category;

import org.example.productmanagment.application.port.in.query.GetCategoryQuery;
import org.example.productmanagment.domain.entities.Category;
import org.springframework.stereotype.Repository;
import org.example.productmanagment.application.port.out.CategoryRepository;

import java.util.List;
import java.util.Optional;


@Repository
public class CategoryRepositoryImp implements CategoryRepository {

    private final SpringDataCategoryRepository categoryDataManagement;

    public CategoryRepositoryImp(SpringDataCategoryRepository categoryDataManagement) {
        this.categoryDataManagement = categoryDataManagement;
    }

    public void save(Category category) {
        CategoryJpaEntity categoryData = this.toEntityData(category);
        this.categoryDataManagement.save(categoryData);
    }

    public Optional<Category> findById(String id) {
        return categoryDataManagement.findById(Long.valueOf(id))
                .map(e -> new Category(
                        e.getId(),
                        e.getName(),
                        e.getDescription(),
                        e.getCreatedAt(),
                        e.getUpdatedAt()
                ));
    }

    public List<Category> findAll() {
        return categoryDataManagement.findAll()
                .stream()
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
    public void deleteById(Long id) {

    }

    public List<Category> fetchCategories(GetCategoryQuery query) {
        return categoryDataManagement.findAll()
                .stream()
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

    public void deleteAll() {
        categoryDataManagement.deleteAll();
    }

    private CategoryJpaEntity toEntityData(Category category) {
        return new CategoryJpaEntity(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}
