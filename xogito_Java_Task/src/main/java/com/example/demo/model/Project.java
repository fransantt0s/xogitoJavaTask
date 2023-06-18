package com.example.demo.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;



@Data
@ToString
@Entity
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
    private List <User> assignedUser;
    private String name;
    private String description;

    public Project(Long id, List<User> assignedUser, String name, String description) {
        this.id = id;
        this.assignedUser = assignedUser;
        this.name = name;
        this.description = description;
        this.assignedUser = new ArrayList<>();
    }
    public Project() {
    }

}
