package com.citronix.demo.dto;

import java.time.LocalDate;

import com.citronix.demo.validation.ValidPlantingDate;

import jakarta.validation.constraints.NotNull;

public record TreeDTO(
        Long id,
        @ValidPlantingDate @NotNull(message = "Planting date is required") LocalDate plantingDate,
        @NotNull(message = "Field ID is required") Long fieldId) {
}