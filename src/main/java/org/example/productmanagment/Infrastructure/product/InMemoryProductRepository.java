package org.example.productmanagment.Infrastructure.product;

import org.example.productmanagment.application.port.in.command.CreateProductCommand;
import org.example.productmanagment.application.port.out.ProductRepository;
import org.example.productmanagment.domain.entities.Category;
import org.example.productmanagment.domain.entities.Product;
import org.example.productmanagment.domain.errors.CategoryNotFoundError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryProductRepository implements ProductRepository {
    private List<Product> products = new ArrayList<>();

    private final List<Category> categories = new ArrayList<>();

    public void save(Product product) {
        this.products.add(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    @Override
    public void deleteById(Long id) {

    }

    public void createProduct(CreateProductCommand command) {
        // Convert values
        double price = Double.parseDouble(command.getPrice());
        int stock = Integer.parseInt(command.getStockQuantity());

        var category = categories.stream()
                .filter(cat -> cat.getName().equals(command.getName()))
                .findFirst()
                .orElseThrow(CategoryNotFoundError::new);

        Product product = new Product(
                command.getName(),
                command.getDescription(),
                price,
                stock,
                command.getCategoryName()
        );
        this.save(product);
    }

    public void addCategory(Category category) {
        categories.add(category);
    }
}
