package co.com.ias.training.core.domain;

import org.apache.commons.lang3.Validate;

public class ProductId {
    private final String value;

    public ProductId(String value) {
        Validate.notBlank(value, "Product id can not be blank.");
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
