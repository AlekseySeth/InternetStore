package service;

import entity.order.Delivery;
import entity.order.Order;
import entity.product.Product;
import entity.user.User;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author a.shestovsky
 */
public class Cart {

    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product, int qtyToAdd) {
        products.put(product, qtyToAdd);
    }

    private BigDecimal calculateSubtotalPrice(Map<Product, Integer> products) {
        BigDecimal subtotalPrice = new BigDecimal(0.0);
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            subtotalPrice.add(entry.getKey().getPrice());
        }
        return subtotalPrice;
    }

    private BigDecimal calculateTotalPrice(BigDecimal subtotalPrice, Delivery delivery) {
        BigDecimal totalPrice = new BigDecimal(subtotalPrice.doubleValue());
        totalPrice.add(delivery.getCost());
        return totalPrice;
    }

    public Order createOrder(Map<Product, Integer> products, User user, Delivery delivery) {
        return null;
    }

}