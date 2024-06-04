package com.maverickstube.maverickshub.services;

import com.maverickstube.maverickshub.dtos.request.CreateUserRequest;
import com.maverickstube.maverickshub.dtos.response.CreateUserResponse;
import com.maverickstube.maverickshub.models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void registerUsersTest(){
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail("test@email.com");
        createUserRequest.setPassword("password");

        CreateUserResponse createUserResponse = userService.register(createUserRequest);
        assertNotNull(createUserResponse);
        assertTrue(createUserResponse.getMessage().contains("success"));
    }
    @Test
    @DisplayName("test that user can be retrieved")
    @Sql(scripts = {"/db/data.sql"})
    public void testGetUserById(){
       User user = userService.getById(200l);
       assertThat(user).isNotNull();
    }
}
