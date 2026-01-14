package org.example.productmanagment.Infrastructure.product;

import org.example.productmanagment.Infrastructure.category.CategoryJpaEntity;
import org.example.productmanagment.Infrastructure.category.SpringDataCategoryRepository;
import org.example.productmanagment.application.port.out.ProductRepository;
import org.example.productmanagment.domain.entities.Product;
import org.example.productmanagment.domain.errors.CategoryNotFoundError;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImp implements ProductRepository {
    private final SpringDataProductRepository productRepo;

    private final SpringDataCategoryRepository categoryRepo;


    public ProductRepositoryImp(SpringDataProductRepository productRepo,
                                SpringDataCategoryRepository categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }


    public void save(Product product) {
        var data = toEntityData(product);
        productRepo.save(data);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }

    private ProductJpaEntity toEntityData(Product product) {
        return new ProductJpaEntity(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCategory()
        );
    }

}
