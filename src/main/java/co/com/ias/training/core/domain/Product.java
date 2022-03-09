package co.com.ias.training.core.domain;

import org.apache.commons.lang3.Validate;

public class Product {
    private final ProductId id;
    private final ProductName name;
    private final ProductDescription description;

    public Product(ProductId id, ProductName name, ProductDescription description) {
        this.id = Validate.notNull(id, "Product id can not be null.");
        this.name = Validate.notNull(name);
        this.description = Validate.notNull(description);
    }

    public ProductId getId() {
        return id;
    }

    public ProductName getName() {
        return name;
    }

    public ProductDescription getDescription() {
        return description;
    }
}
