package dao;

import connection.ConnectionManager;
import entity.user.Role;
import entity.user.User;

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
public class UserDao {

    private static UserDao INSTANCE;

    private UserDao() {
    }

    public static UserDao newInstance() {
        if (INSTANCE == null) {
            synchronized (UserDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserDao();
                }
            }
        }
        return INSTANCE;
    }

    public User save(User user) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "INSERT INTO users (first_name, last_name, email, password, phone, address, registration_date, role_id)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getAddress());
            statement.setDate(7, user.getRegistrationDate());
            statement.setInt(8, user.getRole().ordinal());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
                return user;
            }

            generatedKeys.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM users WHERE id >= 1000 ORDER BY id";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("phone"),
                        resultSet.getString("address"),
                        resultSet.getDate("registration_date"),
                        Role.values()[resultSet.getInt("role_id")]));
            }
            resultSet.close();
            statement.close();
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User get(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM users WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new User(
                        id,
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("phone"),
                        resultSet.getString("address"),
                        resultSet.getDate("registration_date"),
                        Role.values()[resultSet.getInt("role_id")]);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getByEmail(String email) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM users WHERE email=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("phone"),
                        resultSet.getString("address"),
                        resultSet.getDate("registration_date"),
                        Role.values()[resultSet.getInt("role_id")]);
                return user;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(User user) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "UPDATE users SET first_name=?, last_name=?, password=?, phone=?, address=?, role_id=? " +
                    "WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getAddress());
            statement.setInt(6, user.getRole().ordinal());
            statement.setLong(7, user.getId());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updatePassword(User user) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "UPDATE users SET password=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getPassword());
            statement.setLong(2, user.getId());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateProfile(User user) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "UPDATE users SET first_name=?, last_name=?, phone=?, address=? " +
                    "WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getAddress());
            statement.setLong(5, user.getId());

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
            String sql = "DELETE FROM users WHERE id=?";
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