package dao;

import connection.ConnectionManager;
import dto.OrderDto;
import dto.OrderFullDto;
import entity.order.Delivery;
import entity.order.Order;
import entity.order.Status;
import entity.product.Category;
import entity.product.Product;
import entity.user.Role;
import entity.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author a.shestovsky
 */
public class OrderDao {

    private static final int CORRECTION_FACTOR = 1;
    private static OrderDao INSTANCE;

    private OrderDao() {
    }

    public static OrderDao newInstance() {
        if (INSTANCE == null) {
            synchronized (OrderDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new OrderDao();
                }
            }
        }
        return INSTANCE;
    }

    public Order save(Order order) {
        try (Connection connection = ConnectionManager.getConnection()) {
            connection.setAutoCommit(false);
            String orderSql = "INSERT INTO orders (total_price, delivery_id, open_date) VALUES (?, ?, ?)";
            PreparedStatement orderStatement = connection.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
            orderStatement.setBigDecimal(1, order.getTotalPrice());
            orderStatement.setLong(2, order.getDelivery().getId());
            orderStatement.setObject(3, order.getOpenDate());

            orderStatement.executeUpdate();

            ResultSet generatedKeys = orderStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getLong(1));
            }

            String usersOrdersSql = "INSERT INTO users_orders (user_id, order_id) VALUES (?, ?)";
            PreparedStatement usersOrdersStatement = connection.prepareStatement(usersOrdersSql);
            usersOrdersStatement.setLong(1, order.getUser().getId());
            usersOrdersStatement.setLong(2, order.getId());

            usersOrdersStatement.executeUpdate();

            for (Map.Entry<Product, Integer> entry : order.getProducts().entrySet()) {
                String ordersProductsSql = "INSERT INTO orders_products (order_id, product_id, product_qty) " +
                        "VALUES (?, ?, ?)";
                PreparedStatement ordersProductsStatement = connection.prepareStatement(ordersProductsSql);
                ordersProductsStatement.setLong(1, order.getId());
                ordersProductsStatement.setLong(2, entry.getKey().getId());
                ordersProductsStatement.setInt(3, entry.getValue());

                ordersProductsStatement.executeUpdate();
                ordersProductsStatement.close();
            }

            connection.commit();
            generatedKeys.close();
            orderStatement.close();
            usersOrdersStatement.close();
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Order get(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM orders o " +
                    "JOIN users_orders uo ON o.id=uo.order_id " +
                    "JOIN users u ON uo.user_id=u.id " +
                    "JOIN orders_products op ON o.id=op.order_id " +
                    "JOIN products p ON op.product_id=p.id " +
                    "JOIN deliveries d ON o.delivery_id=d.id " +
                    "LEFT JOIN categories c ON p.category_id=c.id " +
                    "JOIN categories pc ON c.parent_id=pc.id " +
                    "WHERE o.id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            Order order = null;

            if (resultSet.next()) {
                order = new Order(
                        id,
                        resultSet.getBigDecimal("o.total_price"),
                        resultSet.getDate("o.open_date").toLocalDate(),
                        resultSet.getDate("o.close_date").toLocalDate());

                order.setStatus(Status.values()[resultSet.getInt("o.status_id") - CORRECTION_FACTOR]);

                order.setUser(new User(
                        resultSet.getLong("u.id"),
                        resultSet.getString("u.first_name"),
                        resultSet.getString("u.last_name"),
                        resultSet.getString("u.email"),
                        resultSet.getString("u.password"),
                        resultSet.getString("u.phone"),
                        resultSet.getString("u.address"),
                        resultSet.getDate("u.registration_date").toLocalDate(),
                        Role.values()[resultSet.getInt("u.role_id")]));

                order.setDelivery(new Delivery(
                        resultSet.getLong("d.id"),
                        resultSet.getString("d.name"),
                        resultSet.getBigDecimal("d.cost")));

                order.addProduct(new Product(
                        resultSet.getLong("p.id"),
                        resultSet.getString("p.name"),
                        resultSet.getString("p.description"),
                        resultSet.getBigDecimal("p.price"),
                        resultSet.getInt("p.qty"),
                        new Category(resultSet.getLong("c.id"),
                                resultSet.getString("c.name"),
                                null,
                                new Category(resultSet.getLong("c.parent_id"),
                                        resultSet.getString("pc.name"),
                                        resultSet.getString("pc.description"),
                                        null)),
                        resultSet.getString("p.image_url")),
                        resultSet.getInt("op.product_qty"));

                while (resultSet.next()) {
                    order.addProduct(new Product(
                                    resultSet.getLong("p.id"),
                                    resultSet.getString("p.name"),
                                    resultSet.getString("p.description"),
                                    resultSet.getBigDecimal("p.price"),
                                    resultSet.getInt("op.product_qty"),
                                    new Category(resultSet.getLong("c.id"),
                                            resultSet.getString("c.name"),
                                            null,
                                            new Category(resultSet.getLong("c.parent_id"),
                                                    resultSet.getString("pc.name"),
                                                    resultSet.getString("c.description"),
                                                    null)),
                                    resultSet.getString("p.image_url")),
                            resultSet.getInt("op.product_qty"));
                }
            }

            resultSet.close();
            statement.close();
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<OrderDto> getByUser(User user) {
        List<OrderDto> orders = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM orders o " +
                    "JOIN users_orders uo ON o.id=uo.order_id " +
                    "JOIN users u ON uo.user_id=u.id " +
                    "WHERE u.id=? ORDER BY o.id";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, user.getId());

            ResultSet resultSet = statement.executeQuery();
            OrderDto orderDto = null;

            while (resultSet.next()) {

                orderDto = new OrderDto(
                        resultSet.getLong("o.id"),
                        resultSet.getBigDecimal("total_price"),
                        resultSet.getDate("open_date").toLocalDate());

                LocalDate closeDate = resultSet.getDate("close_date").toLocalDate();

                if (closeDate != null) {
                    orderDto.setCloseDate(closeDate);
                }

                Status status = Status.values()[resultSet.getInt("status_id") - CORRECTION_FACTOR];
                orderDto.setStatus(status.getAsString());
                orderDto.setUser(user);
                orders.add(orderDto);
            }
            resultSet.close();
            statement.close();
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<OrderFullDto> getAll() {
        List<OrderFullDto> orders = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM orders o " +
                    "JOIN users_orders uo ON o.id=uo.order_id " +
                    "JOIN users u ON uo.user_id=u.id " +
                    "JOIN deliveries d ON o.delivery_id=d.id ORDER BY o.id";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            OrderFullDto orderFullDto = null;

            while (resultSet.next()) {
                Status status = Status.values()[resultSet.getInt("o.status_id") - CORRECTION_FACTOR];
                orderFullDto = new OrderFullDto(
                        resultSet.getLong("o.id"),
                        status.getAsString(),
                        resultSet.getString("d.name"),
                        resultSet.getBigDecimal("o.total_price"),
                        resultSet.getDate("o.open_date").toLocalDate(),
                        resultSet.getDate("o.close_date").toLocalDate(),
                        resultSet.getString("u.email"));
                orders.add(orderFullDto);
            }
            resultSet.close();
            statement.close();
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean update(Order order) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql;
            PreparedStatement statement;
            if (order.getCloseDate() != null) {
                sql = "UPDATE orders SET status_id=?, close_date=? WHERE id=?";
                statement = connection.prepareStatement(sql);
                statement.setLong(1, order.getStatus().ordinal() + CORRECTION_FACTOR);
                statement.setObject(2, order.getCloseDate());
                statement.setLong(3, order.getId());
            } else {
                sql = "UPDATE orders SET status_id=? WHERE id=?";
                statement = connection.prepareStatement(sql);
                statement.setLong(1, order.getStatus().ordinal() + CORRECTION_FACTOR);
                statement.setLong(2, order.getId());
            }
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
            connection.setAutoCommit(false);
            String ordersProducts = "DELETE FROM orders_products WHERE order_id=?";
            String usersOrders = "DELETE FROM users_orders WHERE order_id=?";
            String orders = "DELETE FROM orders WHERE id=?";

            PreparedStatement ordersProductsStatement = connection.prepareStatement(ordersProducts);
            PreparedStatement usersOrdersStatement = connection.prepareStatement(usersOrders);
            PreparedStatement ordersStatement = connection.prepareStatement(orders);

            ordersProductsStatement.setLong(1, id);
            usersOrdersStatement.setLong(1, id);
            ordersStatement.setLong(1, id);

            ordersProductsStatement.executeUpdate();
            usersOrdersStatement.executeUpdate();
            ordersStatement.executeUpdate();

            connection.commit();
            ordersProductsStatement.close();
            usersOrdersStatement.close();
            ordersStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}