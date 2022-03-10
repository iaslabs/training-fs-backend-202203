package co.com.ias.training.infrastructure.controllers;

import co.com.ias.training.infrastructure.controllers.models.ProductDTO;
import co.com.ias.training.infrastructure.controllers.models.ProductInput;
import co.com.ias.training.infrastructure.services.ProductServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductsController {
    private final ProductServices services;

    public ProductsController(ProductServices services) {
        this.services = services;
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ProductDTO createProduct(
            @RequestBody ProductInput productInput
    ) {
        return services.createProduct(productInput);
    }


    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Optional<ProductDTO> getProduct(
            @PathVariable("id") String productId
    ) {
        return services.getProduct(productId);
    }


    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDTO> listProducts(
            @RequestParam(name = "skip", defaultValue = "0") Integer skip,
            @RequestParam(name = "limit", defaultValue = "50") Integer limit
    ) {
        return services.queryProducts(skip, limit);
    }
}
