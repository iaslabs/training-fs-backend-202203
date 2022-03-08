package co.com.ias.training.controllers;

import co.com.ias.training.controllers.models.ProductDTO;
import co.com.ias.training.controllers.models.ProductInput;
import co.com.ias.training.domain.Product;
import co.com.ias.training.domain.ProductDescription;
import co.com.ias.training.domain.ProductId;
import co.com.ias.training.domain.ProductName;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductsController {


    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ProductDTO createProduct(
            @RequestBody ProductInput productInput
    ) {
        Product product = new Product(
                new ProductId(UUID.randomUUID().toString()),
                new ProductName(productInput.getName()),
                new ProductDescription(productInput.getDescription())
        );

        return ProductDTO.fromDomain(product);
    }


    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ProductDTO getProduct(
            @PathVariable("id") String productId
    ) {
        return new ProductDTO(
                productId,
                "fake",
                "fake"
        );
    }


    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDTO> listProducts(
            @RequestParam(name = "skip", defaultValue = "0") Integer skip,
            @RequestParam(name = "limit", defaultValue = "50") Integer limit
    ) {
        return List.of();
    }
}
