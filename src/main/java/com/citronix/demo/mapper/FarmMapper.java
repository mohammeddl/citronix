package com.citronix.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.citronix.demo.dto.FarmDTO;
import com.citronix.demo.model.Farm;

@Mapper(componentModel = "spring")
public interface FarmMapper {

    FarmMapper INSTANCE = Mappers.getMapper(FarmMapper.class);

    FarmDTO toDTO(Farm farm);

    Farm toEntity(FarmDTO farmDTO);
}