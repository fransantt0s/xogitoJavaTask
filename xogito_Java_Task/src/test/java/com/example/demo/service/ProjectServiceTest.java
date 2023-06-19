package com.example.demo.service;

import com.example.demo.dao.ProjectDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.Project;
import com.example.demo.model.User;
import com.example.demo.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProjectServiceTest {

    private ProjectService projectService;
    private ProjectDao projectDao;
    private UserDao userDao;
    private Project project1;
    private Project project2;
    private Project project3;
    private User user1;
    private User user2;
    private User user3;
    private List<User> users;
    private List<Project> projects;

    @BeforeEach
    void setUp(){
        projectDao = mock(ProjectDao.class);
        userDao = mock(UserDao.class);
        projectService = new ProjectServiceImpl(projectDao,userDao);
        project1 = mock(Project.class);
        project2 = mock(Project.class);
        project3 = mock(Project.class);
        projects = new ArrayList<>();
        projects.addAll(Arrays.asList(project1,project2,project3));
        user1 = mock(User.class);
        user2 = mock(User.class);
        user3 = mock(User.class);
        users = new ArrayList<>();
        users.addAll(Arrays.asList(user1,user2,user3));
    }

    @Test
    void testGetAllprojectsShouldReturnANotEmptyList(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Project> page = new PageImpl<>(projects, pageable, projects.size());
        when(projectDao.findAll(pageable)).thenReturn(page);
        List<Project> result = projectService.getAllProjects(0,10);
        assertEquals(projects.size(),projectService.getAllProjects(0,10).size());
        assert(projectService.getAllProjects(0,10).containsAll(projects));
    }

    @Test
    void testAddprojectShouldReturnAString(){
        when(projectDao.save(project1)).thenReturn(project1);
        projectService.saveProject(project1);
        assertEquals(projectService.saveProject(project1),"The project was saved");
        verify(projectDao,times(2)).save(project1);
    }

    @Test
    void testDeleteprojectShouldReturnAString(){
        projectService.saveProject(project1);
        when(project1.getId()).thenReturn(1L);
        when(projectDao.findById(1L)).thenReturn(Optional.of(project1));
        projectService.deleteProject(1L);
        verify(projectDao, times(1)).delete(project1);
    }

    @Test
    void testGetprojectById(){
        when(project1.getId()).thenReturn(1L);
        when(projectDao.save(project1)).thenReturn(project1);
        when(projectDao.findById(1L)).thenReturn(Optional.of(project1));
        projectService.saveProject(project1);
        assertEquals(project1,projectService.getProjectById(1L));
        verify(projectDao).findById(1L);
    }

    @Test
    void testGetprojectByName(){
        when(project1.getName()).thenReturn("Visa");
        when(projectDao.save(project1)).thenReturn(project1);
        when(projectDao.findProjectByName("Visa")).thenReturn(Optional.of(project1));
        projectService.saveProject(project1);
        assertEquals(project1,projectService.getProjectByName("Visa"));
        verify(projectDao).findProjectByName("Visa");
    }

    @Test
    void testAssignUserToProject(){
        when(project2.getId()).thenReturn(2L);
        when(user2.getId()).thenReturn(2L);
        when(projectDao.findById(2L)).thenReturn(Optional.of(project2));
        when(userDao.findById(2L)).thenReturn(Optional.of(user2));
        when(projectDao.findAll()).thenReturn(Collections.singletonList(project2));
        when(userDao.findAll()).thenReturn(Collections.singletonList(user2));
        String result = projectService.assignUser(2L, 2L);
        assertEquals("The user was assigned to the project", result);
        projectService.assignUser(2L,2L);
        verify(projectDao, times(2)).findById(2L);
        verify(userDao, times(2)).findById(2L);
        verify(projectDao, times(2)).save(project2);
    }
}



