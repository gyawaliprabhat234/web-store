package cs.miu.edu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 11:55 AM
 * @project webstore
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Product {
    @Id
    private String productNumber;
    private String name;
    private Double price;
    private String description;
    private Integer numberInStock;
}
