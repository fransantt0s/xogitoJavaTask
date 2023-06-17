package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public List<User> getAllUsers(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<User> pageUser = this.userDao.findAll(pageable);
        List <User> allUsers = pageUser.getContent();
        return allUsers;
    }

    @Override
    public String deleteUser(Long id) {
        User user = userDao.findById(id).orElse(null);
        if(user == null){
            throw  new RuntimeException("The user which you are trying to delete does not exist");
        }
        userDao.delete(user);
        return "The user was deleted";
    }

    @Override
    public User getUserById(Long id) {
        User user = userDao.findById(id).orElse(null);
        if(user == null){
            throw  new RuntimeException("The user which you are looking for does not exist");
        }
        return user;
    }

    @Override
    public String saveUser(User user) {
        if(user == null){
            throw  new RuntimeException("The user which you are trying to save  does not exist");
        }
        userDao.save(user);
        return "The user was added";
    }

    @Override
    public List<User> getUserByName(String name) {
        List<User> usersList = userDao.findByName(name);
        if(usersList.isEmpty()){
            throw  new RuntimeException("The user which you are looking for does not exist");
        }
        return usersList;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userDao.findByEmail(email).orElse(null);
        if(user == null){
            throw  new RuntimeException("The user which you are looking for does not exist");
        }
        return user;
    }

}
