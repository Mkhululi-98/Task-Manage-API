package com.projetmanager.promanager.controller;

import com.projetmanager.promanager.entity.Task;
import com.projetmanager.promanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getById(id);
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task taskDetails) {
        Optional<Task> task = taskService.getById(id);

        if (task.isPresent()) {
            Task updatedTask = task.get();
            updatedTask.setName(taskDetails.getName());
            updatedTask.setDescription(taskDetails.getDescription());
            updatedTask.setCompleted(taskDetails.getCompleted());
            updatedTask.setDueDate(taskDetails.getDueDate());

            // Save the updated task
            Task savedTask = taskService.addTask(updatedTask);
            return ResponseEntity.ok(savedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Task> task = taskService.getById(id);

        if (task.isPresent()) {
            taskService.deleteTaskById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
