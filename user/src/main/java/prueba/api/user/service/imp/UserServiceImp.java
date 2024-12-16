package prueba.api.user.service.imp;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import prueba.api.user.dto.UserDTO;
import prueba.api.user.entity.UserP;
import prueba.api.user.mapper.UserMapper;
import prueba.api.user.repository.UserRepository;
import prueba.api.user.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {

        UserP userPM = UserMapper.mapToUser(userDTO);

        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        userPM.setPassword(hashedPassword);

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

    @Override
    public boolean createUser(UserDTO userDTO) {
        try{
            Optional<UserP> user = userRepository.findByEmail(userDTO.getEmail());
            if (user.isPresent()) {
                return false;
            }
            UserP userP = UserMapper.mapToUser(userDTO);
            //Hash the password
            String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
            userP.setPassword(hashedPassword);
            userRepository.save(userP);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
