package cs.miu.edu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 4:24 PM
 * @project webstore
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Order {
    @Id
    private Integer orderId;
    private User owner;
    private Payment payment;
    private List<OrderedProduct> orderedProducts;
    private OrderStatus status;
}
