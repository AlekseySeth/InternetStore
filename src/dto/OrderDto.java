package dto;

import entity.user.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author a.shestovsky
 */
@Getter
@Setter
public class OrderDto {
    private Long id;
    private String status;
    private BigDecimal totalPrice;
    private LocalDate openDate;
    private LocalDate closeDate;
    private User user;

    public OrderDto(Long id, BigDecimal totalPrice, LocalDate openDate) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.openDate = openDate;
    }
}