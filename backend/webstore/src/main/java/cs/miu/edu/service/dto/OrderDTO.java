package cs.miu.edu.service.dto;

import cs.miu.edu.domain.OrderStatus;
import cs.miu.edu.domain.Payment;
import cs.miu.edu.domain.Product;
import cs.miu.edu.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 9:41 PM
 * @project webstore
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Integer orderId;

    @NotNull
    @Valid
    private UserDTO owner;

    @NotNull
    @Valid
    private PaymentDTO payment;

    @NotNull
    @Valid
    private List<OrderedProductDTO> orderedProducts;

    private OrderStatus status;

    public void setStatus(String status) {
        if (status != null && !status.isEmpty())
            this.status = OrderStatus.getOrderStatus(status);
    }
}
