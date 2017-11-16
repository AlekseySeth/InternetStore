package entity.order;

import entity.product.Product;
import entity.user.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author a.shestovsky
 */

@Getter
@Setter
public class Order {
    private Long id;
    private Status status;
    private BigDecimal totalPrice;
    private Date openDate;
    private Date closeDate;
    private Map<Product, Integer> products = new HashMap<>();
    private User user;
    private Delivery delivery;

    public Order(BigDecimal totalPrice, Date openDate, Map<Product, Integer> products, User user, Delivery delivery) {
        this.totalPrice = totalPrice;
        this.openDate = openDate;
        this.products = products;
        this.user = user;
        this.delivery = delivery;
    }

    public Order(Long id, BigDecimal totalPrice, Date openDate, Date closeDate) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.openDate = openDate;
        this.closeDate = closeDate;
    }

    public void addProduct(Product product, int qty) {
        products.put(product, qty);
    }
}