package co.com.ias.training.infrastructure.gateways.models;

import co.com.ias.training.core.domain.Product;
import co.com.ias.training.core.domain.ProductDescription;
import co.com.ias.training.core.domain.ProductId;
import co.com.ias.training.core.domain.ProductName;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDBO {
    private String id;
    private String name;
    private String description;

    public ProductDBO(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public ProductDBO() {
    }


    public Product toDomain() {
        return new Product(
                new ProductId(id),
                new ProductName(name),
                new ProductDescription(description)
        );
    }

    public static ProductDBO fromDomain(Product product) {
        return new ProductDBO(
                product.getId().toString(),
                product.getName().toString(),
                product.getDescription().toString()
        );
    }

    public static ProductDBO fromResultSet(ResultSet resultSet) throws SQLException {
        ProductDBO dbo = new ProductDBO();
        dbo.setId(resultSet.getString("product_id"));
        dbo.setName(resultSet.getString("product_name"));
        dbo.setDescription(resultSet.getString("product_description"));
        return dbo;
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
