package com.citronix.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.citronix.demo.dto.HarvestDTO;
import com.citronix.demo.model.Harvest;

@Mapper(componentModel = "spring")
public interface HarvestMapper {

    @Mapping(source = "field.id", target = "fieldId")
    HarvestDTO toDTO(Harvest harvest);

    @Mapping(source = "fieldId", target = "field.id")
    Harvest toEntity(HarvestDTO harvestDTO);
}
