package cs.miu.edu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 9:34 PM
 * @project webstore
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customSequences")
public class CustomSequence {
    @Id
    private String id;
    private int seq;

}
