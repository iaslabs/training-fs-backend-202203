package co.com.ias.training.controllers.models;

import co.com.ias.training.domain.Product;

public class ProductDTO {
    private String id;
    private String name;
    private String description;

    public ProductDTO(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public ProductDTO() {
    }

    public static ProductDTO fromDomain(Product product) {
        return new ProductDTO(
                product.getId().toString(),
                product.getName().toString(),
                product.getDescription().toString()
        );
    }


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
