package prueba.api.user.service.imp;

import org.springframework.stereotype.Service;
import prueba.api.user.dto.UserDTO;
import prueba.api.user.entity.UserP;
import prueba.api.user.mapper.UserMapper;
import prueba.api.user.repository.UserRepository;
import prueba.api.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        UserP userP = UserMapper.mapToUser(userDTO);
        UserP userPSaved = userRepository.save(userP);
        return UserMapper.mapToUserDTO(userPSaved);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {

        UserP userPM = UserMapper.mapToUser(userDTO);
        UserP userPSaved = userRepository.save(userPM);
        return UserMapper.mapToUserDTO(userPSaved);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        UserP userP = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return UserMapper.mapToUserDTO(userP);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<UserP> userPS = userRepository.findAll();
        return userPS.stream().map((user) -> UserMapper.mapToUserDTO(user)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String email) {
        UserP userP = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        userRepository.delete(userP);
    }
}
