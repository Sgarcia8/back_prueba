package prueba.api.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import prueba.api.user.dto.UserDTO;

import java.util.List;

public interface UserService {

    boolean createUser(UserDTO user);

    UserDTO updateUser(UserDTO user);

    UserDTO findUserByEmail(String email);

    List<UserDTO> findAllUsers();

    void deleteUser(String email);

}
