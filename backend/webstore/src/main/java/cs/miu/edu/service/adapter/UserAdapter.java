package cs.miu.edu.service.adapter;

import cs.miu.edu.domain.User;
import cs.miu.edu.service.dto.UserDTO;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 10:25 PM
 * @project webstore
 */
public class UserAdapter {

    public static User getUser(UserDTO userDTO){
        User user = new User(userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getPhone(),
                userDTO.getStreet(),
                userDTO.getCity(),
                userDTO.getZip());
        return user;
    }

    public static UserDTO getUserDTO(User user){
        UserDTO userDTO = new UserDTO(user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getStreet(),
                user.getCity(),
                user.getZip());
        return userDTO;
    }

}
