package com.gestiosTares.gestionTareas.taskmanager.repository;

import com.gestiosTares.gestionTareas.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}