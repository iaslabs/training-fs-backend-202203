package co.com.ias.training;

import co.com.ias.training.domain.Product;
import co.com.ias.training.domain.ProductDescription;
import co.com.ias.training.domain.ProductId;
import co.com.ias.training.domain.ProductName;

public class Tests {
    public static void main(String[] args) {
        ProductId productId = new ProductId("  1     ");

        new Product(productId, new ProductName("Product name"), new ProductDescription(""));
    }
}
