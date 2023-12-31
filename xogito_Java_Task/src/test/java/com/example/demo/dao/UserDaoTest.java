package com.example.demo.dao;

import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@DataJpaTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testFindById() {
        User user = userDao.findById(1L).orElse(null);
        assertNotNull(user);
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setEmail("john55@gmail.com");
        user.setName("john");
        User savedUser = userDao.save(user);
        assertNotNull(savedUser);

    }

    @Test
    public void deleteUser() {
        User user = new User();
        user.setEmail("john333@gmail.com");
        user.setName("john");
        User savedUser = userDao.save(user);
        userDao.delete(savedUser);
        assert(!userDao.findAll().contains(user));
    }

    @Test
    public void testFindByName() {
        List<User> users = userDao.findByName("Frank");
        assertNotNull(users);
        assert(!users.isEmpty());
    }

    @Test
    public void testFindByEmail() {
        User user = userDao.findByEmail("frank@gmail.com").orElse(null);
        assertNotNull(user);
    }

}
