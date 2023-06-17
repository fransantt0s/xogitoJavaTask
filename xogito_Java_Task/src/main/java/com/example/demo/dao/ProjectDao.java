package com.example.demo.dao;

import com.example.demo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectDao extends JpaRepository <Project,Long> {
    @Query(value = "SELECT * FROM PROJECT WHERE NAME = ?1", nativeQuery = true)
    Optional<Project> findProjectByName(String name);
}
