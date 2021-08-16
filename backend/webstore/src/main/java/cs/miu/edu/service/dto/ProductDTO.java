package cs.miu.edu.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 12:07 PM
 * @project webstore
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @NotEmpty(message = "Product number cannot be empty")
    private String productNumber;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Price cannot be null")
    private Double price;

    @NotEmpty(message="Description cannot be null")
    @Length(min=5, message="Description must be at least of 5 characters")
    private String description;

    @NotNull(message="Number In Stock cannot be null")
    @Range(min=0, message = "Number of Stock cannot be negative")
    private Integer numberInStock;
}
