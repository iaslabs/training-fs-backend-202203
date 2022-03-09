package co.com.ias.training.infrastructure.gateways;

import co.com.ias.training.core.domain.Product;
import co.com.ias.training.core.domain.ProductId;
import co.com.ias.training.core.gateways.ProductsRepository;
import co.com.ias.training.infrastructure.gateways.models.ProductDBO;
import co.com.ias.training.shared.domain.PageQuery;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SqlProductsRepository implements ProductsRepository {
    private final DataSource dataSource;

    public SqlProductsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> query(PageQuery pageQuery) {
        String sql = "SELECT * FROM products LIMIT ? OFFSET ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, pageQuery.getLimit().getValue());
            preparedStatement.setInt(2, pageQuery.getSkip().getValue());


            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> result = new ArrayList<>();

            while (resultSet.next()) {
                ProductDBO dbo = new ProductDBO();
                dbo.setId(resultSet.getString("product_id"));
                dbo.setName(resultSet.getString("product_name"));
                dbo.setDescription(resultSet.getString("product_description"));
                Product domainProduct = dbo.toDomain();
                result.add(domainProduct);
            }

            resultSet.close();

            return result;
        } catch (SQLException exception) {
            throw new RuntimeException("Error querying database", exception);
        }
    }

    @Override
    public Optional<Product> get(ProductId productId) {
        return Optional.empty();
    }

    @Override
    public void store(Product product) {

    }
}
