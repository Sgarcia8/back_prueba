package prueba.api.user.service;

import prueba.api.user.dto.LoginDTO;
import prueba.api.user.dto.UserDTO;

public interface AuthService {

    boolean createUser(UserDTO userDTO);
}
