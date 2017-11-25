package dao;

import connection.ConnectionManager;
import entity.product.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
        try (Connection connection = ConnectionManager.getConnection()) {
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
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM categories c LEFT JOIN categories p ON c.parent_id=p.id WHERE c.id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            Category category = null;

            if (resultSet.next()) {
                Long parentId = resultSet.getLong("c.parent_id");
                if (parentId == 0) {
                    category = new Category(id, resultSet.getString("c.name"),
                            resultSet.getString("c.description"),
                            null);
                } else {
                    Category parentCategory = new Category(resultSet.getLong("c.parent_id"),
                            resultSet.getString("p.name"),
                            resultSet.getString("p.description"),
                            null);
                    category = new Category(id, resultSet.getString("c.name"), null, parentCategory);
                }
            }

            resultSet.close();
            statement.close();
            return category;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> getParentCategories() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM categories WHERE parent_id IS NULL";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                categories.add(new Category(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        null));
            }

            resultSet.close();
            statement.close();
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> getCategoriesByParent(Category category) {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM categories c LEFT JOIN categories p ON c.parent_id=p.id WHERE c.parent_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, category.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                categories.add(new Category(resultSet.getLong("c.id"),
                        resultSet.getString("c.name"),
                        null,
                        category));
            }

            resultSet.close();
            statement.close();
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Category category) {
        try (Connection connection = ConnectionManager.getConnection()) {
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
        try (Connection connection = ConnectionManager.getConnection()) {
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