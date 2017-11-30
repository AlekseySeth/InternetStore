package dto;

import entity.order.Status;
import entity.user.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author a.shestovsky
 */
@Getter
@Setter
public class OrderDto {
    private Long id;
    private String status;
    private BigDecimal totalPrice;
    private Date openDate;
    private Date closeDate;
    private User user;

    public OrderDto(Long id, BigDecimal totalPrice, Date openDate, Date closeDate) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.openDate = openDate;
        this.closeDate = closeDate;
    }
}
