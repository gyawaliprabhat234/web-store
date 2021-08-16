package cs.miu.edu.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 9:59 PM
 * @project webstore
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotEmpty
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String street;

    @NotEmpty
    private String city;

    @NotEmpty
    private String zip;
}
