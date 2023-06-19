package com.example.demo.service;

import com.example.demo.model.Project;
import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects(Integer pageNumber,Integer pageSize);
    String deleteProject(Long id);
    String saveProject(Project project);
    Project getProjectById(Long id);
    String assignUser(Long  idProject,Long idUser);
    Project getProjectByName(String name);
}
