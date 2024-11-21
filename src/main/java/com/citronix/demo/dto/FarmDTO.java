package com.citronix.demo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public record FarmDTO(
        Long id,
        @NotBlank(message = "Farm name cannot be blank") String name,
        @NotBlank(message = "Localization cannot be blank") String localization,
        @DecimalMin(value = "0.1", message = "Farm surface must be greater than 0.1 hectares") double surface,
        @PastOrPresent(message = "Creation date must be in the past or present") LocalDate creationDate) {
}