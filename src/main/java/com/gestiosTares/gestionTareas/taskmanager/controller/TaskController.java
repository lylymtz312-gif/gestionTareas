package com.gestiosTares.gestionTareas.taskmanager.controller;

import com.gestiosTares.gestionTareas.taskmanager.dto.TaskDto;
import com.gestiosTares.gestionTareas.taskmanager.service.ExternalApiService;
import com.gestiosTares.gestionTareas.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ExternalApiService externalApiService;

    public TaskController(TaskService taskService, ExternalApiService externalApiService) {
        this.taskService = taskService;
        this.externalApiService = externalApiService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto taskDto) {
        TaskDto created = taskService.createTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDto taskDto) {
        TaskDto updated = taskService.updateTask(id, taskDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/external-status")
    public ResponseEntity<String> checkExternalApi() {
        return ResponseEntity.ok(externalApiService.fetchExternalStatus());
    }
}