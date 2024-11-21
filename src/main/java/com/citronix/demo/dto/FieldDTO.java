package com.citronix.demo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public record FieldDTO(
        Long id,
        @NotBlank(message = "name is required") String name,
        @DecimalMin(value = "0.1", message = "Field surface must be at least 0.1 hectares") double surface,
        Long farmId) {
}