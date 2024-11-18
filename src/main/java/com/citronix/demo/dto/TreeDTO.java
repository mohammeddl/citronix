package com.citronix.demo.dto;

import java.time.LocalDate;

import com.citronix.demo.validation.ValidPlantingDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record TreeDTO(
        Long id,
        @ValidPlantingDate @NotNull(message = "Planting date is required") LocalDate plantingDate,
        @PositiveOrZero(message = "Tree age must be zero or positive") int age,
        @NotNull(message = "Field ID is required") Long fieldId) {
}