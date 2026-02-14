package org.example.productmanagment.application.port.in.web.requests.category;

public class GetCategoryRequest {
    private String id;
    private String name;

    public GetCategoryRequest() {
    }

    public GetCategoryRequest(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}