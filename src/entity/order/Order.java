package entity.order;

import entity.product.Product;
import entity.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author a.shestovsky
 */

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Order {
    private Long id;
    private Status status;
    private BigDecimal totalPrice;
    private LocalDate openDate;
    private Date closeDate;
    private Map<Product, Integer> products = new HashMap<>();
    private User user;
    private Delivery delivery;

    public Order(BigDecimal totalPrice, LocalDate openDate, Map<Product, Integer> products, User user, Delivery delivery) {
        this.totalPrice = totalPrice;
        this.openDate = openDate;
        this.products = products;
        this.user = user;
        this.delivery = delivery;
    }

    public Order(Long id, BigDecimal totalPrice, LocalDate openDate, Date closeDate) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.openDate = openDate;
        this.closeDate = closeDate;
    }

    public Order(User user, Delivery delivery) {
        this.user = user;
        this.delivery = delivery;
    }

    public void addProduct(Product product, int qty) {
        products.put(product, qty);
    }

    public void removeProduct(Product product, int qty) {
        products.remove(product, qty);
    }
}