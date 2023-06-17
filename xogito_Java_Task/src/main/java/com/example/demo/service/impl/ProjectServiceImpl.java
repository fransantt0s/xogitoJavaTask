package com.example.demo.service.impl;

import com.example.demo.dao.ProjectDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.Project;
import com.example.demo.model.User;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private ProjectDao projectDao;
    private UserDao userDao;

    @Autowired
    public ProjectServiceImpl(ProjectDao projectDao, UserDao userDao) {
        this.projectDao = projectDao;
        this.userDao = userDao;
    }


    @Override
    public List<Project> getAllProjects(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Project> pageProject = this.projectDao.findAll(pageable);
        List <Project> allProjects = pageProject.getContent();
        return allProjects;
    }

    @Override
    public String deleteProject(Long id) {
        Project project = projectDao.findById(id).orElse(null);
        if (project == null) {
            throw  new RuntimeException("The project which you are trying to delete does not exist");
        }
        projectDao.delete(project);
        return "The project was deleted";
    }

    @Override
    public String saveProject(Project project) {
        if(project == null) {
            throw  new RuntimeException("The project which you are trying to save does not exist");
        }
        projectDao.save(project);
        return "The project was saved";
    }

    @Override
    public Project getProjectById(Long id) {
        Project project = projectDao.findById(id).orElse(null);
        if (project == null) {
            throw  new RuntimeException("The project which you are looking for does not exist");
        }
        return project;
    }

    @Override
    public String assignUser(Long  idProject,Long idUser) {
        Project project = projectDao.findById(idProject).orElse(null);
        User user = userDao.findById(idUser).orElse(null);
        if(!projectDao.findAll().contains(project) || !userDao.findAll().contains(user)) {
            throw new RuntimeException("You cannot assign the user because the project or the user does not exist");
        }
        List <User> assignedUsers = project.getAssignedUser();
        assignedUsers.add(user);
        project.setAssignedUser(assignedUsers);
        projectDao.save(project);
        return "The user was assigned to the project";
    }

    @Override
    public Project getProjectByName(String name) {
        Project project = projectDao.findProjectByName(name).orElse(null);
        if (project == null) {
            throw  new RuntimeException("The project which you are looking for does not exist");
        }
        return project;
    }
}
