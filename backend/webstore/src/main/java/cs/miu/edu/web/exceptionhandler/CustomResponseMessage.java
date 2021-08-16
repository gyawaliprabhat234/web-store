package cs.miu.edu.web.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author Prabhat Gyawali
 * @created 24-Jun-2021 - 6:20 PM
 * @project lab3restapi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponseMessage {
    private String message;
    private HttpStatus status;
}
