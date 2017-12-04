package entity.product;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author a.shestovsky
 */

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {"category", "qtyInStock"})
public class Product {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int qtyInStock;
    private Category category;
    private String imageURL;

    public Product(String name, String description, BigDecimal price, int qtyInStock, Category category, String imageURL) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.qtyInStock = qtyInStock;
        this.category = category;
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(name);
        while (builder.length() <= 60) {
            builder.append(" ");
        }
        builder.append(price).append(" руб.");
        while (builder.length() <= 85) {
            builder.append(" ");
        }
        return builder.toString();
    }
}