package dev.matheuslf.desafio.inscritos.service;

import dev.matheuslf.desafio.inscritos.dto.TaskCreateDTO;
import dev.matheuslf.desafio.inscritos.entities.Project;
import dev.matheuslf.desafio.inscritos.entities.Task;
import dev.matheuslf.desafio.inscritos.entities.enums.Status;
import dev.matheuslf.desafio.inscritos.exceptions.ResourceNotFoundException;
import dev.matheuslf.desafio.inscritos.repositories.ProjectRepository;
import dev.matheuslf.desafio.inscritos.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public Task createTask(TaskCreateDTO dto) {
        Project project = projectRepository.findById(dto.projectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project with id " + dto.projectId() + " not found"));

        Task newTask = new Task();
        newTask.setTitle(dto.title());
        newTask.setDescription(dto.description());
        newTask.setStatus(dto.status());
        newTask.setPriority(dto.priority());
        newTask.setDueDate(dto.dueDate());
        newTask.setProjectId(project.getId());
        return taskRepository.save(newTask);
    }

    public List<Task> searchTasks(Status status, Long projectId) {
        if (status == null && projectId == null) {
            return getAllTasks();
        }
        if (status != null && projectId == null) {
            return getAllByStatus(status);
        }
        if (status == null && projectId != null) {
            return getAllByProjectId(projectId);
        }
        return getAllByStatusAndProjectId(status, projectId);
    }

    private List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    private List<Task> getAllByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }

    private List<Task> getAllByProjectId(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    private List<Task> getAllByStatusAndProjectId(Status status, Long projectId) {
        return taskRepository.findByStatusAndProjectId(status, projectId);
    }

    public void updateTask(Long id, Status status) {
        Optional<Task> oldTask = taskRepository.findById(id);
        if (oldTask.isEmpty()) {
            throw new ResourceNotFoundException("Task with id " + id + " not found");
        }

        Task newTask = oldTask.get();
        newTask.setStatus(status);
        taskRepository.save(newTask);
    }

    public void deleteTask(Long id) {
        Optional<Task> oldTask = taskRepository.findById(id);
        if (oldTask.isEmpty()) {
            throw new ResourceNotFoundException("Task with id " + id + " not found");
        }
        taskRepository.deleteById(id);
    }
}
