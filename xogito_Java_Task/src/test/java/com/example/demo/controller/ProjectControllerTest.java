package com.example.demo.controller;

import com.example.demo.model.Project;
import com.example.demo.model.User;
import com.example.demo.service.ProjectService;
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

public class ProjectControllerTest {
    private ProjectService projectService;
    private ProjectController projectController;

    private User user1;
    private User user2;
    private User user3;

    private List<User> users;


    private Project project1;
    private Project project2;
    private Project project3;

    private List<Project>projects;


    @BeforeEach
    void setUp(){
        user1 = mock(User.class);
        user2 = mock(User.class);
        user3 = mock(User.class);
        users = new ArrayList<>();
        users.addAll(Arrays.asList(user1,user2,user3));
        projectService = mock(ProjectService.class);
        projectController = new ProjectController(projectService);
        project1 = mock(Project.class);
        when(project1.getAssignedUser()).thenReturn(users);
        project2 = mock(Project.class);
        project3 = mock(Project.class);
        projects = new ArrayList<>();
        projects.addAll(Arrays.asList(project1,project2,project3));
    }

    @Test
    void getAllProjectsTestShouldReturn200HttpStatusOK(){
        when(projectService.getAllProjects(0,3)).thenReturn(projects);
        ResponseEntity result =projectController.getAllProjects(0,3);
        assertThat(result,notNullValue());
        assertThat(result.getStatusCode(),equalTo(HttpStatus.OK));
        assertThat(result.getBody(),notNullValue());
        verify(projectService).getAllProjects(0,3);

    }

    @Test
    void getProjectByIdTestShouldReturn200HttpStatusOK(){
        when(projectService.getProjectById(2L)).thenReturn(project2);
        ResponseEntity result =projectController.getProjectById(2L);
        assertThat(result,notNullValue());
        assertThat(result.getStatusCode(),equalTo(HttpStatus.OK));
        assertThat(result.getBody(),notNullValue());
        verify(projectService).getProjectById(2L);

    }

    @Test
    void getprojectByNameTestShouldReturn200HttpStatusOK(){
        when(projectService.getProjectByName("Visa")).thenReturn(project2);
        ResponseEntity result = projectController.getProjectByName("Visa");
        assertThat(result,notNullValue());
        assertThat(result.getStatusCode(),equalTo(HttpStatus.OK));
        assertThat(result.getBody(),notNullValue());
        verify(projectService).getProjectByName("Visa");

    }


    @Test
    void testAddProjectShouldReturn200HttpStatusCode(){
        Project project4 = mock(Project.class);
        ResponseEntity response = projectController.saveProject(project4);
        assertThat(response,notNullValue());
        assertThat(response.getStatusCode(),equalTo(HttpStatus.CREATED));
    }

    @Test
    void testDeleteProjectShouldReturn200HttpStatusCode(){
        when(project1.getId()).thenReturn(1L);
        ResponseEntity response = projectController.deleteProjectById(1L);
        assertThat(response,notNullValue());
        assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
        verify(projectService).deleteProject(1L);

    }

    @Test
    void testAssignUserToProjectShouldReturn200HttpStatusCode(){
        User user4 = mock(User.class);
        when(user4.getId()).thenReturn(4L);
        when(project2.getId()).thenReturn(2L);
        ResponseEntity response = projectController.assignUserToProject(2L,4L);
        assertThat(response,notNullValue());
        assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
    }

}
