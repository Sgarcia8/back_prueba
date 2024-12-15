package prueba.api.user.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import prueba.api.user.dto.UserDTO;
import prueba.api.user.entity.UserP;
import prueba.api.user.mapper.UserMapper;
import prueba.api.user.repository.UserRepository;
import prueba.api.user.service.AuthService;

@Service
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean createUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            return false;
        }

        UserP userP = UserMapper.mapToUser(userDTO);

        //Hash the password
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        userP.setPassword(hashedPassword);
        userRepository.save(userP);

        return true;
    }
}
