package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository <User,Long>{
    @Query(value = "SELECT * FROM USER_TABLE WHERE EMAIL = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM USER_TABLE WHERE NAME = ?1", nativeQuery = true)
    List<User> findByName(String name);


}
