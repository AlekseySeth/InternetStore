package service;

import dao.OrderDao;
import dto.OrderDto;
import entity.order.Order;
import entity.product.Product;
import entity.user.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public List<Order> getAllOrders() {

        return null;
    }

    public Order getOrderById(Long id) {
        return OrderDao.newInstance().get(id);
    }

    public Order updateOrder() {

        return null;
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