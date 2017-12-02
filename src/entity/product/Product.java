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

    @Override
    public String toString() {
        return id + ". " + name + " - " + description + " | " + price + "$ | " + qtyInStock + " | " + category.getName();
    }
}