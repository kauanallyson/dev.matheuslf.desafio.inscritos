package dev.matheuslf.desafio.inscritos.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ProjectCreateDTO(
        @NotNull
        @Size(min = 3, max = 100)
        String name,

        String description,

        LocalDate startDate,

        @Future
        LocalDate endDate
) {
}
