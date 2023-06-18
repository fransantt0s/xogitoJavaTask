package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    private UserService userService;
    private UserController userController;

    private User user1;
    private User user2;
    private User user3;

    private List<User> users;


    @BeforeEach
    void setUp(){
        userService = mock(UserService.class);
        userController = new UserController(userService);
        user1 = mock(User.class);
        user2 = mock(User.class);
        user3 = mock(User.class);
        users = new ArrayList<>();
        users.addAll(Arrays.asList(user1,user2,user3));
    }

    @Test
    void getAllUsersTestShouldReturn200HttpStatusOK(){
        when(userService.getAllUsers(0,3)).thenReturn(users);
        ResponseEntity result = userController.getAllUsers(0,3);
        assertThat(result,notNullValue());
        assertThat(result.getStatusCode(),equalTo(HttpStatus.OK));
        assertThat(result.getBody(),notNullValue());
        verify(userService).getAllUsers(0,3);

    }

    @Test
    void getUserByIdTestShouldReturn200HttpStatusOK(){
        when(userService.getUserById(2L)).thenReturn(user2);
        ResponseEntity result = userController.getUserById(2L);
        assertThat(result,notNullValue());
        assertThat(result.getStatusCode(),equalTo(HttpStatus.OK));
        assertThat(result.getBody(),notNullValue());
        verify(userService).getUserById(2L);

    }

    @Test
    void getUserByNameTestShouldReturn200HttpStatusOK(){
        when(user2.getName()).thenReturn("John");
        when(user3.getName()).thenReturn("John");
        List <User> usersWithSameName = Arrays.asList(user2,user3);
        when(userService.getUserByName("John")).thenReturn(usersWithSameName);
        ResponseEntity result = userController.getUserByName("John");
        assertThat(result,notNullValue());
        assertThat(result.getStatusCode(),equalTo(HttpStatus.OK));
        assertThat(result.getBody(),notNullValue());
        verify(userService).getUserByName("John");

    }

    @Test
    void getUserByEmailTestShouldReturn200HttpStatusOK(){
        when(user2.getEmail()).thenReturn("john@gmail.com");
        when(userService.getUserByEmail("john@gmail")).thenReturn(user2);
        ResponseEntity result = userController.getUserByEmail("john@gmail.com");
        assertThat(result,notNullValue());
        assertThat(result.getStatusCode(),equalTo(HttpStatus.OK));
        verify(userService).getUserByEmail("john@gmail.com");

    }


    @Test
    void testAddUserShouldReturn200HttpStatusCode(){
        User user4 = mock(User.class);
        ResponseEntity response = userController.saveUser(user4);
        assertThat(response,notNullValue());
        assertThat(response.getStatusCode(),equalTo(HttpStatus.CREATED));
    }

    @Test
    void testDeleteUserShouldReturn200HttpStatusCode(){
        when(user1.getId()).thenReturn(1L);
        ResponseEntity response = userController.deleteUserById(1L);
        assertThat(response,notNullValue());
        assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
        verify(userService).deleteUser(1L);

    }

}
