package com.projetmanager.promanager.repository;

import com.projetmanager.promanager.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
