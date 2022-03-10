package co.com.ias.training.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductIdTest {

    @Test
    @DisplayName("Null productId should throw an error")
    void null_product_id_tests() {
        // A = Arrange, A = Act, A = Assert
        // Arrange
        String unsafeId = null;

        // Assert
        assertThrows(NullPointerException.class, () -> {
            // act
            new ProductId(unsafeId);
        });
    }

    @Test
    @DisplayName("Invalid productId should throw an error")
    void invalid_product_id_tests() {
        // A = Arrange, A = Act, A = Assert
        // Arrange
        String unsafeId = "";

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // act
            new ProductId(unsafeId);
        });
    }


    @Test
    @DisplayName("A valid Id should not throw an error")
    void valid_id_test() {
        String validId = UUID.randomUUID().toString();
        assertDoesNotThrow(() -> {
            new ProductId(validId);
        });
    }
}