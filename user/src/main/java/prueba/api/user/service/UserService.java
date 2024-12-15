package prueba.api.user.service;

import prueba.api.user.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO user);

    UserDTO findUserByEmail(String email);

    List<UserDTO> findAllUsers();

    void deleteUser(String email);
}
