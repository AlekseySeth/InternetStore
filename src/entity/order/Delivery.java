package entity.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author a.shestovsky
 */

@Getter
@Setter
@AllArgsConstructor
public class Delivery {
    private Long id;
    private String name;
    private BigDecimal cost;

    public Delivery(String name, BigDecimal cost) {
        this.name = name;
        this.cost = cost;
    }
}