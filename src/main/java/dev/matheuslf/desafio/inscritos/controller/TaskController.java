package dev.matheuslf.desafio.inscritos.controller;

import dev.matheuslf.desafio.inscritos.dto.TaskCreateDTO;
import dev.matheuslf.desafio.inscritos.entities.Task;
import dev.matheuslf.desafio.inscritos.entities.enums.Status;
import dev.matheuslf.desafio.inscritos.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Void> createTask(
            @Valid @RequestBody TaskCreateDTO dto) {
        Task createdTask = taskService.createTask(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Task>> searchTasks(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Long projectId) {
        return new ResponseEntity<>(taskService.searchTasks(status, projectId), HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateTask(
            @PathVariable Long id,
            @RequestBody Status status
    ) {
        taskService.updateTask(id, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long id
    ){
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
