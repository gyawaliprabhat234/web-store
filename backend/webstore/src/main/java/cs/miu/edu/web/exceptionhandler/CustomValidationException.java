package cs.miu.edu.web.exceptionhandler;

import cs.miu.edu.service.dto.ProductDTO;
import lombok.NoArgsConstructor;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 8:34 PM
 * @project webstore
 */
@NoArgsConstructor
public class CustomValidationException extends Exception {

    private Set<ConstraintViolation<Object>> violations;

    private CustomValidationException(Set<ConstraintViolation<Object>> violations){
        this.violations = violations;
    }

    public static void ifNotValidThrowValidationException(Validator validator, Object object) throws CustomValidationException {
        Set<ConstraintViolation<Object>> violations =   validator.validate(object, Default .class);
        if(!violations.isEmpty())
            throw new CustomValidationException(violations);
    }

    public Map<String, String>  getViolations(){
        return this.violations.stream()
                .collect(Collectors.toMap(key-> key.getPropertyPath().toString(),
                        value-> value.getMessage().toString()));
    }
}
