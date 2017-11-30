package service;

import dao.OrderDao;
import dto.OrderDto;
import dto.OrderFullDto;
import entity.order.Order;
import entity.order.Status;
import entity.product.Product;
import entity.user.Role;
import entity.user.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @author a.shestovsky
 */
public class OrderService {

    private static final String PATH = "web" + File.separator + "invoices";
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

    public List<OrderDto> getOrdersByUser(User user) {
        return OrderDao.newInstance().getByUser(user);
    }

    public List<Order> getOrdersBetweenDate() {

        return null;
    }

    public List<Order> getOrdersByProductContains() {

        return null;
    }

    public List<OrderFullDto> getAllOrders() {
        return OrderDao.newInstance().getAll();
    }

    public Order getOrderById(Long id) {
        return OrderDao.newInstance().get(id);
    }

    public boolean updateOrder(Order order, String status) {
        if (Status.valueOf(status).equals(Status.CLOSED) || Status.valueOf(status).equals(Status.COMPLETED)) {
            order.setStatus(Status.valueOf(status));
            order.setCloseDate(new Date(System.currentTimeMillis()));
        } else {
            order.setStatus(Status.valueOf(status));
        }
        return true;
    }

    public File generateInvoice(Long orderId, String fileName) {
        Order order = OrderService.newInstance().getOrderById(orderId);
        File invoice = new File(PATH, fileName);
        Map<Product, Integer> products = order.getProducts();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(invoice))) {
            writer.write("Заказ №" + order.getId()
            + "           Наименование продукта                            Цена                 Количество");
            for (Map.Entry entry : products.entrySet()) {
                writer.write(((Product) entry.getKey()).getName() + "        " + ((Product) entry.getKey()).getPrice() + " руб." + "       " + entry.getValue());
            }
            writer.write("Доставка: " + order.getDelivery().getName());
            writer.write("Итого " + order.getTotalPrice() + "руб.");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return invoice;
    }
}