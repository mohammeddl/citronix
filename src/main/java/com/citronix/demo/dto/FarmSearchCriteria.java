package com.citronix.demo.dto;


import java.time.LocalDate;

public record FarmSearchCriteria(
    String name,
    String localization,
    Double minSurface,
    Double maxSurface,
    LocalDate createdBefore,
    LocalDate createdAfter
) {}
