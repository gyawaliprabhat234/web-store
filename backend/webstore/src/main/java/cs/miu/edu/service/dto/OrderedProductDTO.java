package cs.miu.edu.service.dto;

import cs.miu.edu.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author Prabhat Gyawali
 * @created 13-Jul-2021 - 2:21 PM
 * @project webstore
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProductDTO {
    @NotNull
    private ProductDTO product;

    @NotNull
    @Range(min = 1, message = "Quantity must be greater than 0")
    private Integer quantity;
}
