package com.citronix.demo.dto;

import java.time.LocalDate;

public record TreeDTO(
    Long id,
    LocalDate plantingDate,
    int age,
    Long fieldId
) {}