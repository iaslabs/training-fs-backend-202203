package co.com.ias.training.domain;

import org.apache.commons.lang3.Validate;

public class ProductDescription {
    private final String value;

    public ProductDescription(String value) {
        Validate.notNull(value, "Product description can not be null.");
        Validate.isTrue(value.trim().length() < 512, "Product description can not be longer than 512");
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
