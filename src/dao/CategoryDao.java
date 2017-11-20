package dao;

import connection.ConnectionManager;
import entity.product.Category;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author a.shestovsky
 */
public class CategoryDao {

    private static CategoryDao INSTANCE;

    private CategoryDao() {
    }

    public static CategoryDao newInstance() {
        if (INSTANCE == null) {
            synchronized (CategoryDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CategoryDao();
                }
            }
        }
        return INSTANCE;
    }

    public Category save(Category category) {
        try (Connection connection = ConnectionManager.dataSource.getConnection()) {
            String sql = "INSERT INTO categories (name, description, parent_id) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setLong(3, category.getCategory().getId());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                category.setId(generatedKeys.getLong(1));
                return category;
            }

            generatedKeys.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Category get(Long id) {
        try (Connection connection = ConnectionManager.dataSource.getConnection()) {
            String sql = "SELECT * FROM categories c JOIN categories p ON c.parent_id=p.id WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            Category category = null;

            if (resultSet.next()) {
                Category parentCategory = new Category(resultSet.getLong("c.parent_id"),
                        resultSet.getString("p.name"),
                        resultSet.getString("p.description"),
                        null);
                category = new Category(id, resultSet.getString("c.name"), null, parentCategory);
            }

            resultSet.close();
            statement.close();
            return category;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Category category) {
        try (Connection connection = ConnectionManager.dataSource.getConnection()) {
            String sql = "UPDATE categories SET name=?, description=?, parent_id=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setLong(3, category.getCategory().getId());
            statement.setLong(4, category.getId());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.dataSource.getConnection()) {
            String sql = "DELETE FROM categories WHERE id=?";
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