package dev.matheuslf.desafio.inscritos.service;

import dev.matheuslf.desafio.inscritos.dto.ProjectCreateDTO;
import dev.matheuslf.desafio.inscritos.entities.Project;
import dev.matheuslf.desafio.inscritos.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void createProject(ProjectCreateDTO dto) {
        Project newProject = new Project();
        newProject.setName(dto.name());
        newProject.setDescription(dto.description());
        newProject.setStartDate(dto.startDate());
        newProject.setEndDate(dto.endDate());
        projectRepository.save(newProject);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}

