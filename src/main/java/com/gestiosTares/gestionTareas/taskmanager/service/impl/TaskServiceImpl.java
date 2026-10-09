package com.gestiosTares.gestionTareas.taskmanager.service.impl;

import com.gestiosTares.gestionTareas.taskmanager.dto.TaskDto;
import com.gestiosTares.gestionTareas.taskmanager.entity.Task;
import com.gestiosTares.gestionTareas.taskmanager.exception.TaskNotFoundException;
import com.gestiosTares.gestionTareas.taskmanager.repository.TaskRepository;
import com.gestiosTares.gestionTareas.taskmanager.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskDto> getAllTasks() {
        // Programación Funcional usando Stream API para mapear entidades a DTOs
        return taskRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public TaskDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarea no encontrada con ID: " + id));
        return convertToDto(task);
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Task task = convertToEntity(taskDto);
        Task savedTask = taskRepository.save(task);
        return convertToDto(savedTask);
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarea no encontrada con ID: " + id));

        existingTask.setTitle(taskDto.getTitle());
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setPriority(taskDto.getPriority());
        existingTask.setDueDate(taskDto.getDueDate());
        existingTask.setStatus(taskDto.getStatus());

        Task updatedTask = taskRepository.save(existingTask);
        return convertToDto(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Tarea no encontrada con ID: " + id);
        }
        taskRepository.deleteById(id);
    }

    private TaskDto convertToDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setPriority(task.getPriority());
        dto.setDueDate(task.getDueDate());
        dto.setStatus(task.getStatus());
        return dto;
    }

    private Task convertToEntity(TaskDto dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());
        task.setStatus(dto.getStatus());
        return task;
    }
}
