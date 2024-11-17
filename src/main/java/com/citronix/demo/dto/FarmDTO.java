package com.citronix.demo.dto;

import java.time.LocalDate;

public record FarmDTO(
    Long id,
    String name,
    String localization,
    double surface,
    LocalDate creationDate
) {}