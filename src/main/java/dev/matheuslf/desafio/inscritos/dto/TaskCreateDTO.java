package dev.matheuslf.desafio.inscritos.dto;

import dev.matheuslf.desafio.inscritos.entities.enums.Priority;
import dev.matheuslf.desafio.inscritos.entities.enums.Status;

import java.time.LocalDate;

public record TaskCreateDTO(
        String title,
        String description,
        Status status,
        Priority priority,
        LocalDate dueDate,
        Long projectId
) {
}
