package org.example.productmanagment.domain.entities;

import org.example.productmanagment.domain.errors.CategoryNameTooLongError;
import org.example.productmanagment.domain.errors.EmptyCategoryNameError;

import java.time.LocalDateTime;
import java.util.Objects;

public class Category {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    private void validateCategory() {
        if (name == null || name.trim().isEmpty()) {
            throw new EmptyCategoryNameError();
        }
        if (name.length() > 100) {
            throw new CategoryNameTooLongError();
        }
    }

    public void updateDetails(String name, String description) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
        if (description != null) {
            this.description = description;
        }
        this.updatedAt = LocalDateTime.now();

        validateCategory();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
