package co.com.ias.training.core.domain;

import org.apache.commons.lang3.Validate;

public class ProductId {
    private final String value;

    public ProductId(String value) {
        Validate.notBlank(value, "Product id can not be blank.");
        Validate.isTrue(value.length() == 36, "Invalid product id");
        this.value = value;
    }

    public static ProductId unsafeCreate(String unsafeValue) {
        return new ProductId(unsafeValue);
    }


    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
