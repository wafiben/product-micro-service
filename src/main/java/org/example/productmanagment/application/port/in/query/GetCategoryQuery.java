package org.example.productmanagment.application.port.in.query;

public class GetCategoryQuery {
    private String id;

    private String name;

    public GetCategoryQuery(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}