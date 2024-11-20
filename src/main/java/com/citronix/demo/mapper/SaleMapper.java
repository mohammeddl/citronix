package com.citronix.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.citronix.demo.dto.SaleDTO;
import com.citronix.demo.model.Sale;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    SaleMapper INSTANCE = Mappers.getMapper(SaleMapper.class);

    @Mapping(source = "harvestId", target = "harvest.id")
    Sale toEntity(SaleDTO saleDTO);

    @Mapping(source = "harvest.id", target = "harvestId")
    SaleDTO toDTO(Sale sale);
}
