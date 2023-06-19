package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;

@Data
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project")
    private Long id;
    @ManyToMany
    @JoinTable (
            name = "user_project",
            joinColumns = { @JoinColumn(name = "id_project") },
            inverseJoinColumns = { @JoinColumn(name = "id_user") }
    )
    private List <User> assignedUsers;
    @NotBlank(message = "Project's name cannot be empty")
    private String name;
    private String description;

}
