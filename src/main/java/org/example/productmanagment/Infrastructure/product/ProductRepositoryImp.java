package org.example.productmanagment.Infrastructure.product;

import org.example.productmanagment.Infrastructure.category.CategoryJpaEntity;
import org.example.productmanagment.Infrastructure.category.SpringDataCategoryRepository;
import org.example.productmanagment.application.port.out.ProductRepository;
import org.example.productmanagment.domain.entities.Category;
import org.example.productmanagment.domain.entities.Product;
import org.example.productmanagment.domain.errors.CategoryNotFoundError;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImp implements ProductRepository {

    private final SpringDataProductRepository productRepo;

    private final SpringDataCategoryRepository categoryRepository;

    public ProductRepositoryImp(SpringDataProductRepository productRepo, SpringDataCategoryRepository categoryRepository) {
        this.productRepo = productRepo;
        this.categoryRepository = categoryRepository;
    }


    public void save(Product product) {
        var data = toEntityData(product);
        productRepo.save(data);
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        Map<Long, String> categoryNames = categoryRepository.findAll()
                .stream()
                .collect(Collectors.toMap(
                        CategoryJpaEntity::getId,
                        CategoryJpaEntity::getName)
                );

        return productRepo.findAll().stream()
                .map(e -> toDomain(e, categoryNames.get(e.getCategoryId())))
                .toList();
    }

    @Override
    public void deleteById(Long id) {

    }

    public void deleteAll() {
        productRepo.deleteAll();
    }

    private ProductJpaEntity toEntityData(Product product) {
        Long categoryId = categoryRepository.findByName(product.getCategory())
                .map(CategoryJpaEntity::getId)
                .orElseThrow(CategoryNotFoundError::new);


        return new ProductJpaEntity(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                categoryId
        );
    }

    private Product toDomain(ProductJpaEntity e, String categoryName) {
        return new Product(
                e.getId(),
                e.getName(),
                e.getDescription(),
                e.getPrice(),
                e.getStockQuantity(),
                categoryName,
                toInstant(e.getCreatedAt()),
                toInstant(e.getUpdatedAt())
        );
    }

    private Instant toInstant(LocalDateTime value) {
        return value == null ? null : value.atZone(ZoneId.systemDefault()).toInstant();
    }
}



