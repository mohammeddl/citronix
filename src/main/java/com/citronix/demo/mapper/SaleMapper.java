package com.citronix.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.citronix.demo.dto.SaleDTO;
import com.citronix.demo.model.Sale;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    SaleMapper INSTANCE = Mappers.getMapper(SaleMapper.class);

    Sale toEntity(SaleDTO saleDTO);

    SaleDTO toDTO(Sale sale);
}
