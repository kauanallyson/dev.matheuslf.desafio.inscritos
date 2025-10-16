package dev.matheuslf.desafio.inscritos.controller;

import dev.matheuslf.desafio.inscritos.dto.ProjectCreateDTO;
import dev.matheuslf.desafio.inscritos.entities.Project;
import dev.matheuslf.desafio.inscritos.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // POST - /projects
    @PostMapping
    public void createProject(
            @Valid @RequestBody ProjectCreateDTO dto){
        projectService.createProject(dto);
    }

    // GET - /projects
    @GetMapping
    public List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }
}
