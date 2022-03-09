package co.com.ias.training;

import co.com.ias.training.core.domain.Product;
import co.com.ias.training.core.domain.ProductDescription;
import co.com.ias.training.core.domain.ProductId;
import co.com.ias.training.core.domain.ProductName;

public class Tests {
    public static void main(String[] args) {
        ProductId productId = new ProductId("  1     ");

        new Product(productId, new ProductName("Product name"), new ProductDescription(""));
    }
}
