package com.example.demo.controller;

import com.example.demo.model.Project;
import com.example.demo.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/projects")
@Api(tags = "Xogito API")
public class ProjectController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @ApiOperation("Get all projects")
    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAllProjects(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        try {
            return ResponseEntity.ok(projectService.getAllProjects(pageNumber,pageSize));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation("Delete project by id")
    @RequestMapping(value = "/delete/{id}",method = {RequestMethod.DELETE})
    public ResponseEntity <Object> deleteProjectById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(projectService.deleteProject(id));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The project was not found, so it was not deleted");
        }
    }

    @ApiOperation("Get project by id")
    @GetMapping("/get/id/{id}")
    public ResponseEntity <Object> getProjectById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(projectService.getProjectById(id));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The project was not found");
        }
    }

    @ApiOperation("Get project by name")
    @GetMapping("/get/name/{name}")
    public ResponseEntity <Object> getProjectByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(projectService.getProjectByName(name));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The project was not found");
        }
    }

    @ApiOperation("Save a new project")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Object> saveProject(@Valid @RequestBody Project project, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            for (FieldError error : bindingResult.getFieldErrors()) {
                String fieldName = error.getField();
                String errorMessage = error.getDefaultMessage();
                logger.error("Field error {}: {}", fieldName, errorMessage);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Project was not saved,Please review the fields");
        }
        try {
            projectService.saveProject(project);
            return ResponseEntity.status(HttpStatus.CREATED).body(project);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Project was not saved.");
        }
    }

    @ApiOperation("Assign a user to a project")
    @RequestMapping(value = "/{idProject}/{idUser}",method = {RequestMethod.PUT})
    public ResponseEntity <Object> assignUserToProject(@PathVariable Long idProject, @PathVariable Long idUser) {
        try {
            return ResponseEntity.ok(projectService.assignUser(idProject,idUser));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The project or the user were not found, the project was not assigned");
        }
    }
}
