package co.com.ias.training.infrastructure.controllers;

import co.com.ias.training.core.gateways.ProductsRepository;
import co.com.ias.training.infrastructure.controllers.models.ProductDTO;
import co.com.ias.training.infrastructure.controllers.models.ProductInput;
import co.com.ias.training.core.domain.Product;
import co.com.ias.training.core.domain.ProductDescription;
import co.com.ias.training.core.domain.ProductId;
import co.com.ias.training.core.domain.ProductName;
import co.com.ias.training.shared.domain.Limit;
import co.com.ias.training.shared.domain.PageQuery;
import co.com.ias.training.shared.domain.Skip;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class ProductsController {
    private final ProductsRepository productsRepository;

    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }


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
        PageQuery pageQuery = new PageQuery(
                new Skip(skip),
                new Limit(limit)
        );
        List<Product> products = productsRepository.query(pageQuery);

        List<ProductDTO> result = new ArrayList<>();
        for (Product product : products) {
            ProductDTO dto = ProductDTO.fromDomain(product);
            result.add(dto);
        }
        return result;
    }
}
