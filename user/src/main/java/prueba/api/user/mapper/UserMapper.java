package prueba.api.user.mapper;

import prueba.api.user.dto.UserDTO;
import prueba.api.user.entity.UserP;

public class UserMapper {
    public static UserDTO mapToUserDTO(UserP userP) {
        return new UserDTO(
                userP.getId(),
                userP.getName(),
                userP.getEmail(),
                userP.getPassword(),
                userP.getAddress(),
                userP.getStatus(),
                userP.isTokenRevoked(),
                userP.getCreateDate(),
                userP.getRole()
        );
    }

    public static UserP mapToUser(UserDTO userDTO) {
        return new UserP(
                userDTO.getId(),
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getAddress(),
                userDTO.getStatus(),
                userDTO.isTokenRevoked(),
                userDTO.getCreateDate(),
                userDTO.getRole()
        );
    }
}
