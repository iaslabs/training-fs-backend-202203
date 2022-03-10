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
            preparedStatement.setInt(2, pageQuery.getSkip().toInteger());


            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> result = new ArrayList<>();

            while (resultSet.next()) {
                ProductDBO productDBO = ProductDBO.fromResultSet(resultSet);
                Product product = productDBO.toDomain();
                result.add(product);
            }

            resultSet.close();

            return result;
        } catch (SQLException exception) {
            throw new RuntimeException("Error querying database", exception);
        }
    }

    @Override
    public Optional<Product> get(ProductId productId) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, productId.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                ProductDBO dbo = ProductDBO.fromResultSet(resultSet);
                Product domainProduct = dbo.toDomain();
                return Optional.of(domainProduct);
            } else {
                return Optional.empty();
            }

        } catch (SQLException exception) {
            throw new RuntimeException("Error querying database", exception);
        }
    }

    @Override
    public void store(Product product) {
        String sql = "INSERT INTO products (product_id, product_name, product_description) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, product.getId().toString());
            preparedStatement.setString(2, product.getName().toString());
            preparedStatement.setString(3, product.getDescription().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Error querying database", exception);
        }
    }
}
