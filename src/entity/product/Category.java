package entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author a.shestovsky
 */

@Getter
@Setter
@AllArgsConstructor
public class Category {
    private Long id;
    private String name;
    private Category category;
}