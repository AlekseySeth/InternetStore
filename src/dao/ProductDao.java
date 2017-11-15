package dao;

import connection.ConnectionManager;
import entity.product.Category;
import entity.product.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author a.shestovsky
 */
public class ProductDao {

    private static ProductDao INSTANCE;

    private ProductDao() {
    }

    public static ProductDao newInstance() {
        if (INSTANCE == null) {
            synchronized (ProductDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ProductDao();
                }
            }
        }
        return INSTANCE;
    }

    public Product save(Product product) {
        try (Connection connection = ConnectionManager.newConnection()) {
            String sql = "INSERT INTO products (name, description, price, qty, category_id, image_url) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setBigDecimal(3, product.getPrice());
            statement.setInt(4, product.getQtyInSock());
            statement.setLong(5, product.getCategory().getId());
            statement.setString(6, product.getImageURL());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong(1));
                return product;
            }

            generatedKeys.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Product get(Long id) {
        try (Connection connection = ConnectionManager.newConnection()) {
            String sql = "SELECT p.name, p.description, p.price, p.qty, p.image_url, c.id, c.category " +
                    "FROM products p JOIN categories c ON p.category_id=c.id WHERE p.id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            Product product = null;

            if (resultSet.next()) {
                Category category = new Category(
                        resultSet.getLong("c.id"),
                        resultSet.getString("c.name"));

                product = new Product(
                        id,
                        resultSet.getString("p.name"),
                        resultSet.getString("p.description"),
                        resultSet.getBigDecimal("p.price"),
                        resultSet.getInt("p.qty"),
                        category,
                        resultSet.getString("p.image_url"));
            }

            resultSet.close();
            statement.close();
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Product product) {
        try (Connection connection = ConnectionManager.newConnection()) {
            String sql = "UPDATE products SET name=?, description=?, price=?, qty=?, category_id=?, image_url=? " +
                    "WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setBigDecimal(3, product.getPrice());
            statement.setInt(4, product.getQtyInSock());
            statement.setString(5, product.getImageURL());
            statement.setLong(6, product.getId());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.newConnection()) {
            String sql = "DELETE FROM products WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}