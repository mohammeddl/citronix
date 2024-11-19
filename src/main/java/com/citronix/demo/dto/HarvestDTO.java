package com.citronix.demo.dto;

import java.time.LocalDate;
import java.util.List;

import com.citronix.demo.model.Season;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record HarvestDTO(

        Long id,
        @NotNull(message = "Season is required") Season season,
        @NotNull(message = "Harvest date is required") LocalDate date,
        @Positive(message = "Harvest quantity must be positive") double quantity,
        @NotNull(message = "Field ID is required") Long fieldId,
        @Valid List<@NotNull(message = "Tree harvest details are required") TreeHarvestDetailDTO> treeHarvestDetails) {
}
