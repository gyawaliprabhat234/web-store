package cs.miu.edu.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 9:59 PM
 * @project webstore
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    @NotEmpty
    private String creditCardType;

    @NotEmpty
    @CreditCardNumber(message = "Enter a valid card number")
    private String number;

    @NotNull
    @Future(message = "Expired card is not accepted")
    private LocalDate validDate;

    @NotEmpty
    private String validationCode;
}
