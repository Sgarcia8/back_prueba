package prueba.api.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prueba.api.user.dto.LoginDTO;
import prueba.api.user.dto.UserDTO;
import prueba.api.user.mapper.UserMapper;
import prueba.api.user.responses.AuthResponse;
import prueba.api.user.responses.ErrorResponse;
import prueba.api.user.responses.ResponseMessage;
import prueba.api.user.service.UserService;
import prueba.api.user.service.imp.CustomerUserDetailService;
import prueba.api.user.utils.JwtUtil;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final CustomerUserDetailService customerUserDetailService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(UserService userService, CustomerUserDetailService customerUserDetailService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.customerUserDetailService = customerUserDetailService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/register")
    public ResponseEntity<?> signupUser(@RequestBody UserDTO userDTO) {
        boolean isIserCreated = userService.createUser(userDTO);
        if (isIserCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Usuario creado con éxito"));
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Error al crear el usuario"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginDTO loginDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword())
            );
        }catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Credenciales inválidas"));
        }
        UserDetails userDetails;
        UserDTO user;

        try {
            userDetails = customerUserDetailService.loadUserByUsername(loginDTO.getEmail());
            user = userService.findUserByEmail(loginDTO.getEmail());
        }catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Usuario no encontrado"));
        }

        String role = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElse("ROLE_USER");

        String jwt = jwtUtil.generateToken(userDetails.getUsername(), role);

        //Retorna un objeto con el token de acceso y la información del usuario que se esta logueando
        return ResponseEntity.ok(new AuthResponse(jwt, UserMapper.mapToUser(user)));
    }
}
