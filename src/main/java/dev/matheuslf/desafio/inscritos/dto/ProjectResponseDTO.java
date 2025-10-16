package dev.matheuslf.desafio.inscritos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ProjectResponseDTO(
        Long id,

        @NotNull
        @Size(min = 3, max = 100)
        String name,

        String description,

        LocalDate startDate,

        LocalDate endDate
) {
}
