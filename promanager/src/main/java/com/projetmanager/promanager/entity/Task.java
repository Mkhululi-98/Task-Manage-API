package com.projetmanager.promanager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Boolean completed;
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @JsonBackReference// Foreign key referencing Project entity
    private Project project;

    // Manual setter for id
    public void setId(Long id) {
        this.id = id;
    }

    // Manual setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Manual setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Manual setter for completed
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    // Manual setter for dueDate
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // Manual getter for id
    public Long getId() {
        return id;
    }

    // Manual getter for name
    public String getName() {
        return name;
    }

    // Manual getter for description
    public String getDescription() {
        return description;
    }

    // Manual getter for completed
    public Boolean getCompleted() {
        return completed;
    }

    // Manual getter for dueDate
    public LocalDate getDueDate() {
        return dueDate;
    }

    // Manual getter for project
    public Project getProject() {
        return project;
    }

    // Setter for project (if needed)
    public void setProject(Project project) {
        this.project = project;
    }
}
