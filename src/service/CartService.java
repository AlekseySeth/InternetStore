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

    public List<Delivery> getAllDeliveries() {
        return DeliveryDao.newInstance().getAll();
    }

    public void setOrderDelivery(Order order, Long deliveryId) {
        Delivery delivery = DeliveryDao.newInstance().get(deliveryId);
        order.setDelivery(delivery);
    }

    public BigDecimal calculateSubtotalPrice(Order order) {
        BigDecimal subtotalPrice = new BigDecimal(0.0);
        for (Map.Entry<Product, Integer> entry : order.getProducts().entrySet()) {
            BigDecimal productPrice = entry.getKey().getPrice();
            BigDecimal productQty = BigDecimal.valueOf(entry.getValue());
            subtotalPrice = subtotalPrice.add(productPrice.multiply(productQty));
        }
        return subtotalPrice;
    }

    public BigDecimal calculateTotalPrice(Order order) {
        BigDecimal totalPrice = new BigDecimal(0.0);
        totalPrice = totalPrice
                .add(calculateSubtotalPrice(order))
                .add(order.getDelivery().getCost());
        return totalPrice;
    }

    public Order placeOrder(Order order) {
        order.setOpenDate(new Date(System.currentTimeMillis()));
        return OrderDao.newInstance().save(order);
    }
}