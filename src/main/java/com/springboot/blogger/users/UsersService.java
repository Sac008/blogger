package com.springboot.blogger.users;

import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private UsersRepository usersRepository;
    private ModelMapper modelMapper;

    public UsersService(@Autowired UsersRepository usersRepository , ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * signup user
     */

    public UserDTO.LoginUserResponse signupUser(UserDTO.CreateUserRequest user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        UserEntity savedUser = usersRepository.save(userEntity);
        UserDTO.LoginUserResponse response = modelMapper.map(savedUser , UserDTO.LoginUserResponse.class);
        response.setToken("token"); // TODO: generate token for logged in users
        return response;
    }

    /**
     * login user
     */

    public UserDTO.LoginUserResponse loginUser(UserDTO.LoginUserRequest user) {
        UserEntity userEntity = usersRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> new UserNotFoundException(user.getUsername())
        );

        // TODO: match password using hashing
        if(userEntity.getPassword().equals(user.getPassword())) {
            UserDTO.LoginUserResponse response = modelMapper.map(userEntity , UserDTO.LoginUserResponse.class);
            response.setToken("token");
            return response;
        }

        else {
            throw new UserAuthenticationException();
        }

    }

    /**
     * Exceptions
     */

    static class UserNotFoundException extends SecurityException {
        public UserNotFoundException(String username) {
            super("No such user found with username: " + username);
        }
    }

    static class UserAuthenticationException extends SecurityException {
        public UserAuthenticationException() {
            super("Authentication failed");
        }
    }


}
