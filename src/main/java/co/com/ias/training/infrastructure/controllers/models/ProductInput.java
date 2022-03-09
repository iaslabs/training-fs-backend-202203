package co.com.ias.training.infrastructure.controllers.models;

public class ProductInput {
    private String name;
    private String description;


    public ProductInput(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ProductInput() {
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
}
