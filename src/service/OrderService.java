package service;

import entity.order.Order;
import entity.user.User;

import java.util.List;

/**
 * @author a.shestovsky
 */
public class OrderService {

    private static OrderService INSTANCE;

    private OrderService() {
    }

    public static OrderService newInstance() {
        if (INSTANCE == null) {
            synchronized (OrderService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new OrderService();
                }
            }
        }
        return INSTANCE;
    }

    public List<Order> getOrdersByUser() {

        return null;
    }

    public List<Order> getOrdersBetweenDate() {

        return null;
    }

    public List<Order> getOrdersByProductContains() {

        return null;
    }

    public List<Order> getAllOrders() {

        return null;
    }

    public Order getOrderById() {

        return null;
    }

    public Order updateOrder() {

        return null;
    }

    public void downloadOrderFile() {

    }

}