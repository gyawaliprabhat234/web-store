package cs.miu.edu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 4:22 PM
 * @project webstore
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private String creditCardType;
    private String number;
    private LocalDate validDate;
    private String validationCode;
}
