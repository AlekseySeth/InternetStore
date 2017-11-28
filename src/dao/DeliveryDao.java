package dao;

import connection.ConnectionManager;
import entity.order.Delivery;

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
public class DeliveryDao {

    private static DeliveryDao INSTANCE;

    private DeliveryDao() {
    }

    public static DeliveryDao newInstance() {
        if (INSTANCE == null) {
            synchronized (DeliveryDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DeliveryDao();
                }
            }
        }
        return INSTANCE;
    }

    public Delivery save(Delivery delivery) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "INSERT INTO deliveries (name, cost) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, delivery.getName());
            statement.setBigDecimal(2, delivery.getCost());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                delivery.setId(generatedKeys.getLong(1));
                return delivery;
            }

            generatedKeys.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Delivery get(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT name, cost FROM deliveries WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            Delivery delivery = null;

            if (resultSet.next()) {
                delivery = new Delivery(
                        id,
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("cost"));
            }

            resultSet.close();
            statement.close();
            return delivery;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Delivery> getAll() {
        List<Delivery> deliveries = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM deliveries";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                deliveries.add(new Delivery(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("cost")));
            }

            resultSet.close();
            statement.close();
            return deliveries;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Delivery delivery) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "UPDATE deliveries SET name=?, cost=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, delivery.getName());
            statement.setBigDecimal(2, delivery.getCost());
            statement.setLong(3, delivery.getId());

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
            String sql = "DELETE FROM deliveries WHERE id=?";
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