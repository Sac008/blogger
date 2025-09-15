package com.springboot.blogger.users;

import com.springboot.blogger.common.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersContoller {

    private UsersService usersService;

    public UsersContoller(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/register")
    ResponseEntity<UserDTO.LoginUserResponse> signupUser(
            @RequestBody UserDTO.CreateUserRequest request
    ) {
        UserDTO.LoginUserResponse response = usersService.signupUser(request);
        return ResponseEntity.created(
                URI.create("/users" + response.getId())
        ).body(response);
    }

    @PostMapping("/login")
    ResponseEntity<UserDTO.LoginUserResponse> loginUser(
            @RequestBody UserDTO.LoginUserRequest request
    ) {
        UserDTO.LoginUserResponse response = usersService.loginUser(request);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler()
    ResponseEntity<ErrorDTO> exceptionHandler(Exception ex) {
        if(ex instanceof UsersService.UserNotFoundException) {
            return ResponseEntity.status(404).body(new ErrorDTO(ex.getMessage()));
        }
        if(ex instanceof UsersService.UserAuthenticationException) {
            return ResponseEntity.status(401).body(new ErrorDTO(ex.getMessage()));
        }
        return ResponseEntity.status(500).body(new ErrorDTO(ex.getMessage()));
    }
}
