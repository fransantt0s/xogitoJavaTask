package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers (Integer pageNumber, Integer pageSize);
    String deleteUser(Long id);
    User getUserById(Long id);
    String saveUser(User user);
    List<User> getUserByName(String name);
    User getUserByEmail(String email);
}
