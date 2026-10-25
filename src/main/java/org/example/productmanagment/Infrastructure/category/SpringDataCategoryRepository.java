package org.example.productmanagment.Infrastructure.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataCategoryRepository extends JpaRepository<CategoryJpaEntity, Long> {
    Optional<CategoryJpaEntity> findByName(String name);
}
