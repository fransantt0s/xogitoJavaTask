package com.example.demo.service;

import com.example.demo.dao.ProjectDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.Project;
import com.example.demo.model.User;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserService userService;
    private UserDao userDao;
    private User user1;
    private User user2;
    private User user3;
    private ProjectDao projectDao;
    private List<User> users;

    @BeforeEach
    void setUp(){
        userDao = mock(UserDao.class);
        projectDao = mock(ProjectDao.class);
        userService = new UserServiceImpl(userDao,projectDao);
        user1 = mock(User.class);
        user2 = mock(User.class);
        user3 = mock(User.class);
        users = new ArrayList<>();
        users.addAll(Arrays.asList(user1,user2,user3));
    }

    @Test
    void testGetAllUsersShouldReturnANotEmptyList(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<User> page = new PageImpl<>(users, pageable, users.size());
        when(userDao.findAll(pageable)).thenReturn(page);
        assertEquals(users.size(),userService.getAllUsers(0,10).size());
        assert(userService.getAllUsers(0,10).containsAll(users));
    }

    @Test
    void testAddUserShouldReturnAString(){
        when(userDao.save(user1)).thenReturn(user1);
        userService.saveUser(user1);
        assertEquals(userService.saveUser(user1),"The user was added");
        verify(userDao,times(2)).save(user1);
    }

    @Test
    void testDeleteUserShouldReturnAString(){
        userService.saveUser(user1);
        List<Project> projects = new ArrayList<>();
        when(projectDao.findAll()).thenReturn(projects);
        when(user1.getId()).thenReturn(1L);
        when(userDao.findById(1L)).thenReturn(Optional.of(user1));
        userService.deleteUser(1L);
        verify(userDao, times(1)).delete(user1);
    }

    @Test
    void testGetUserById(){
        when(user1.getId()).thenReturn(1L);
        when(userDao.save(user1)).thenReturn(user1);
        when(userDao.findById(1L)).thenReturn(Optional.of(user1));
        userService.saveUser(user1);
        assertEquals(user1,userService.getUserById(1L));
        verify(userDao).findById(1L);
    }

    @Test
    void testGetUserByName(){
        when(user1.getName()).thenReturn("John");
        when(userDao.save(user1)).thenReturn(user1);
        when(user2.getName()).thenReturn("John");
        when(userDao.save(user2)).thenReturn(user1);
        List <User> usersWithSameName = Arrays.asList(user1,user2);
        when(userDao.findByName("John")).thenReturn(usersWithSameName);
        userService.saveUser(user1);
        assertEquals(2,userService.getUserByName("John").size());
    }

    @Test
    void testGetUserByEmail(){
        when(user1.getEmail()).thenReturn("john@gmail.com");
        when(userDao.save(user1)).thenReturn(user1);
        when(userDao.findByEmail("john@gmail.com")).thenReturn(Optional.of(user1));
        userService.saveUser(user1);
        assertEquals(user1,userService.getUserByEmail("john@gmail.com"));
        verify(userDao).findByEmail("john@gmail.com");
    }

}
