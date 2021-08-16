package cs.miu.edu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 4:20 PM
 * @project webstore
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String email;
    private String phone;
    private String street;
    private String city;
    private String zip;
}
