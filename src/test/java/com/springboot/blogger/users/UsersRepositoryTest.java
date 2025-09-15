package com.springboot.blogger.users;

import org.apache.catalina.User;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    @Order(1)
    void can_create_users() {
        usersRepository.save(
                UserEntity.builder()
                        .username("Shyam Singh")
                        .email("Shyam@Meta.com")
                        .build());
    }

//    @Test
//    @Order(2)
//    void can_find_users_by_username() {
//        usersRepository.save(
//                UserEntity.builder()
//                        .username("sachin Singh")
//                        .email("sachin@Meta.com")
//                        .build());
//        usersRepository.save(
//                UserEntity.builder()
//                        .username("Ram Singh")
//                        .email("ram@Meta.com")
//                        .build());
//        UserEntity user = usersRepository.findByUsername("sachin Singh").orElseThrow(
//                () -> new RuntimeException("user not found")
//        );
//        assertEquals(user.getUsername() , "sachin Singh");
//    }


}
