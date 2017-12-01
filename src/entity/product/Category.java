package entity.product;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author a.shestovsky
 */

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Category {
    private Long id;
    private String name;
    private String description;
    private Category category;

    public Category(String name, Category category) {
        this.name = name;
        this.category = category;
    }
}