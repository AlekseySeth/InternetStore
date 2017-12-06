package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author a.shestovsky
 */
@Getter
@Setter
@AllArgsConstructor
public class OrderFullDto {
    private Long id;
    private String status;
    private String delivery;
    private BigDecimal totalPrice;
    private LocalDate openDate;
    private LocalDate closeDate;
    private String userEmail;

}