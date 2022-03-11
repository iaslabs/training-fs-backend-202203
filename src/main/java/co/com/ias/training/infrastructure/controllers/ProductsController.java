package co.com.ias.training.infrastructure.controllers;

import co.com.ias.training.infrastructure.controllers.models.ProductDTO;
import co.com.ias.training.infrastructure.controllers.models.ProductInput;
import co.com.ias.training.infrastructure.controllers.services.ProductServices;
import co.com.ias.training.shared.errors.ApplicationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ProductsController {
    private final ProductServices services;

    public ProductsController(ProductServices services) {
        this.services = services;
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(
            @RequestBody ProductInput productInput
    ) {
        try {
            ProductDTO product = services.createProduct(productInput);
            return ResponseEntity.ok(product);
        } catch (IllegalArgumentException | NullPointerException e) {
            ApplicationError error = new ApplicationError(
                    "InputDataValidationError",
                    "Bad input data",
                    Map.of(
                            "error", e.getMessage()
                    )
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(error);
        } catch (Exception e) {
            ApplicationError error = new ApplicationError(
                    "SystemError",
                    e.getMessage(),
                    Map.of()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }
    }


    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getProduct(
            @PathVariable("id") String productId
    ) {
        Optional<ProductDTO> product = services.getProduct(productId);
        if(product.isPresent()) {
            return ResponseEntity.ok(product);
        } else {
            ApplicationError error = new ApplicationError(
                    "ResourceNotFound",
                    "Product with id not found",
                    Map.of("id", productId)
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
    }


    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDTO> listProducts(
            @RequestParam(name = "skip", defaultValue = "0") Integer skip,
            @RequestParam(name = "limit", defaultValue = "50") Integer limit
    ) {
        return services.queryProducts(skip, limit);
    }
}
