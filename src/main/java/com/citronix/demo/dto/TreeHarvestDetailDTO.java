package com.citronix.demo.dto;

import jakarta.validation.constraints.NotNull;

public record TreeHarvestDetailDTO(
    Long id,
    @NotNull(message = "Tree ID is required")Long treeId,
    double quantity
) {}