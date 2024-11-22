package com.citronix.demo.dto;


import java.time.LocalDate;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record FarmSearchCriteria(
    @Size(min = 1, max = 100) String name,
    @Size(min = 1, max = 100) String localization,
    @PositiveOrZero Double minSurface,
    @PositiveOrZero Double maxSurface,
    @PastOrPresent LocalDate createdBefore,
    @PastOrPresent LocalDate createdAfter
) {}
