package service;

import dao.DeliveryDao;
import dao.OrderDao;
import entity.order.Delivery;
import entity.order.Order;
import entity.product.Product;
import entity.user.User;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @author a.shestovsky
 */
@Getter
public class CartService {

    private static CartService INSTANCE;

    private CartService() {
    }

    public static CartService newInstance() {
        if (INSTANCE == null) {
            synchronized (CartService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CartService();
                }
            }
        }
        return INSTANCE;
    }

    public void addProductToCart(Order order, Long productId, int qtyToAdd) {
        Product product = CatalogService.newInstance().getProductById(productId);
        if (product.getQtyInSock() >= qtyToAdd) {
            order.addProduct(product, qtyToAdd);
        } else {
//            сообщение, что не хватает на складе
        }
    }

    private BigDecimal calculateTotalPrice(Map<Product, Integer> products, Delivery delivery) {
        BigDecimal totalPrice = new BigDecimal(0.0);
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            totalPrice = totalPrice.add(entry.getKey().getPrice());
        }
        totalPrice = totalPrice.add(delivery.getCost());
        return totalPrice;
    }

    public Order placeOrder(Order order, Map<Product, Integer> products, User user, Delivery delivery) {
        order.setTotalPrice(calculateTotalPrice(products, delivery));
        order.setOpenDate(new Date(System.currentTimeMillis()));
        return OrderDao.newInstance().save(order);
    }

    public List<Delivery> getAllDeliveries() {
        return DeliveryDao.newInstance().getAll();
    }
}