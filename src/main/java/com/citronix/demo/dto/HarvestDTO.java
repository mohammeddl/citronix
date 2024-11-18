package com.citronix.demo.dto;

import java.time.LocalDate;
import java.util.List;

import com.citronix.demo.model.Season;

public record HarvestDTO(
    Long id,
    Season season,
    LocalDate date,
    double quantity,
    Long fieldId,
    List<TreeHarvestDetailDTO> treeHarvestDetails
) {}
