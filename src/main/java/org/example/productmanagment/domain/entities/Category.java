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

    // Constructor for creating a new category
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        validateCategory();
    }

    // Constructor for setting all fields (e.g., loading from DB or in-memory repository)
    public Category(Long id, String name, String description,
                    LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        validateCategory();
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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
