package co.com.ias.training.domain;

import org.apache.commons.lang3.Validate;

public class ProductName {
    private final String value;

    public ProductName(String value) {
        Validate.notBlank(value, "Product name can not be blank.");
        Validate.isTrue(value.trim().length() < 128, "Product name can not be longer than 128");
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
