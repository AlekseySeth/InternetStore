package service;

import entity.order.Delivery;
import entity.order.Order;
import entity.product.Product;
import entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
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

    public void addProductToCart(Product product, int qtyToAdd) {
        // использовать куки, чтобы определенный пользователь добавлял товары в свою корзину
        Order order = new Order();
        order.addProduct(product, qtyToAdd);
    }

    private BigDecimal calculateSubtotalPrice(Map<Product, Integer> products) {
        BigDecimal subtotalPrice = new BigDecimal(0.0);
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            subtotalPrice = subtotalPrice.add(entry.getKey().getPrice());
        }
        return subtotalPrice;
    }

    private BigDecimal calculateTotalPrice(BigDecimal subtotalPrice, Delivery delivery) {
        BigDecimal totalPrice = new BigDecimal(subtotalPrice.doubleValue());
        totalPrice = totalPrice.add(delivery.getCost());
        return totalPrice;
    }

    //переделать
    public Order placeOrder(Order order, Map<Product, Integer> products, User user, Delivery delivery) {
        BigDecimal subtotalPrice = calculateSubtotalPrice(products);
        BigDecimal totalPrice = calculateTotalPrice(subtotalPrice, delivery);
        Date openDate = null;
        order.setTotalPrice(totalPrice);
        return new Order(totalPrice, openDate, products, user, delivery);
    }
}