package com.citronix.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.citronix.demo.dto.FieldDTO;
import com.citronix.demo.model.Field;

@Mapper(componentModel = "spring")
public interface FieldMapper {

    FieldMapper INSTANCE = Mappers.getMapper(FieldMapper.class);

    @Mapping(source = "farm.id", target = "farmId")
    FieldDTO toDTO(Field field);

    @Mapping(source = "farmId", target = "farm.id")
    Field toEntity(FieldDTO fieldDTO);
    

    List<FieldDTO> toDTOList(List<Field> fields);
}