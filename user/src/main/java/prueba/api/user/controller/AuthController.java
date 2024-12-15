package prueba.api.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prueba.api.user.dto.LoginDTO;
import prueba.api.user.dto.UserDTO;
import prueba.api.user.service.AuthService;
import prueba.api.user.service.UserService;
import prueba.api.user.service.imp.CustomerUserDetailService;
import prueba.api.user.utils.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;


    private final AuthenticationManager authenticationManager;

    private final CustomerUserDetailService customerUserDetailService;

    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthService authService, AuthenticationManager authenticationManager, CustomerUserDetailService customerUserDetailService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.customerUserDetailService = customerUserDetailService;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/register")
    public ResponseEntity<String> signupUser(@RequestBody UserDTO userDTO) {
        boolean isIserCreated = authService.createUser(userDTO);
        if (isIserCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado con exito");
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el usuario");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword())
            );
        }catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDetails userDetails;

        try {
            userDetails = customerUserDetailService.loadUserByUsername(loginDTO.getEmail());
        }catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(jwt);
    }
}
