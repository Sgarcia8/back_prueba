package prueba.api.user.service.imp;

import org.springframework.security.core.userdetails.User;
import prueba.api.user.entity.UserP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import prueba.api.user.repository.UserRepository;

import java.util.Collections;

@Service
public class CustomerUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        UserP usuario = userRepository.findByEmail(correo).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (usuario.getRole() == null || usuario.getRole().getName() == null) {
            throw new IllegalStateException("El usuario no tiene un rol asignado");
        }

        return new User(
                usuario.getEmail(),
                usuario.getPassword(),

                Collections.singleton(new SimpleGrantedAuthority("ROLE_"+ usuario.getRole().getName()))
        );

    }
}
