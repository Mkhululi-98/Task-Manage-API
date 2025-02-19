package com.projetmanager.promanager.repository;

import com.projetmanager.promanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
