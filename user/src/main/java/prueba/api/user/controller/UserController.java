package prueba.api.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prueba.api.user.dto.UserDTO;
import prueba.api.user.responses.ResponseMessage;
import prueba.api.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/public")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }


    @DeleteMapping("/admin/{email}")
    public ResponseEntity<?> deleteUsuario(@PathVariable("email") String email){
        userService.deleteUser(email);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Usuario eliminado con éxito"));
    }

    @PostMapping("/admin/actualizar/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO){
        userDTO.setId(id);
        UserDTO userAct = userService.updateUser(userDTO);
        return ResponseEntity.ok(userAct);
    }
}
