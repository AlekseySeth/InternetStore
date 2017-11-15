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

    }

    private BigDecimal calculateTotalPrice(Map<Product, Integer> products, Delivery delivery) {

        return null;
    }

    public Order createOrder(Map<Product, Integer> products, User user, Delivery delivery) {
        return null;
    }

}