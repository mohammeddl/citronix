package com.citronix.demo.dto;

public record FieldDTO(
    Long id,
    String name,
    double surface,
    Long farmId
) {}