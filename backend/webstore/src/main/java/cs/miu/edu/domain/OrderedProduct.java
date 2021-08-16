package cs.miu.edu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Prabhat Gyawali
 * @created 13-Jul-2021 - 2:21 PM
 * @project webstore
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProduct {
    private Product product;
    private Integer quantity;
}
