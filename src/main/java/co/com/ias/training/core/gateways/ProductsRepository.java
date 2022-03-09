package co.com.ias.training.core.gateways;

import co.com.ias.training.core.domain.Product;
import co.com.ias.training.core.domain.ProductId;
import co.com.ias.training.shared.domain.PageQuery;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository {
    List<Product> query(PageQuery pageQuery);

    Optional<Product> get(ProductId productId);

    void store(Product product);
}
