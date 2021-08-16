package cs.miu.edu.web.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Prabhat Gyawali
 * @created 24-Jun-2021 - 2:26 PM
 * @project lab3restapi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceNotFoundException extends Exception{
    private String message;
}
