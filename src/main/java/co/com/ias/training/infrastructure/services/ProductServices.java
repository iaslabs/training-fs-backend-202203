package co.com.ias.training.infrastructure.services;

import co.com.ias.training.core.domain.Product;
import co.com.ias.training.core.domain.ProductDescription;
import co.com.ias.training.core.domain.ProductId;
import co.com.ias.training.core.domain.ProductName;
import co.com.ias.training.core.gateways.ProductsRepository;
import co.com.ias.training.infrastructure.controllers.models.ProductDTO;
import co.com.ias.training.infrastructure.controllers.models.ProductInput;
import co.com.ias.training.shared.domain.Limit;
import co.com.ias.training.shared.domain.PageQuery;
import co.com.ias.training.shared.domain.Skip;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServices {
    private final ProductsRepository repository;

    public ProductServices(ProductsRepository repository) {
        this.repository = repository;
    }

    public ProductDTO createProduct(
            ProductInput productInput
    ) {
        String value = UUID.randomUUID().toString();
        try {
            Product product = new Product(
                    new ProductId(value),
                    new ProductName(productInput.getName()),
                    new ProductDescription(productInput.getDescription())
            );
            repository.store(product);

            return ProductDTO.fromDomain(product);
        } catch (NullPointerException | IllegalArgumentException e) {
           throw new IllegalArgumentException("Error validating product data", e);
        }
    }


    public List<ProductDTO> queryProducts(Integer skip, Integer limit) {
        PageQuery pageQuery = new PageQuery(
                new Skip(skip),
                new Limit(limit)
        );
        List<Product> products = repository.query(pageQuery);

        List<ProductDTO> result = new ArrayList<>();
        for (Product product : products) {
            ProductDTO dto = ProductDTO.fromDomain(product);
            result.add(dto);
        }
        return result;
    }

    public Optional<ProductDTO> getProduct(String productId) {
        return repository.get(new ProductId(productId))
                .map(product -> {
                    return ProductDTO.fromDomain(product);
                });
    }
}
