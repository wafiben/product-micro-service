package org.example.productmanagment.application.port.in.command;

public class CreateProductCommand {

    private String name;
    private String description;
    private String price;           // String for validation at boundary
    private String stockQuantity;   // String for validation at boundary
    private String categoryName;

    // Empty constructor (for JSON deserialization)
    public CreateProductCommand() {
    }

    // Full constructor
    public CreateProductCommand(
            String name,
            String description,
            String price,
            String stockQuantity,
            String categoryName
    ) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryName = categoryName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(String stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
