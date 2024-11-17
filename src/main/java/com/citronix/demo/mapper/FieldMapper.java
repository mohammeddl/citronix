package com.citronix.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.citronix.demo.dto.FieldDTO;
import com.citronix.demo.model.Field;

@Mapper(componentModel = "spring")
public interface FieldMapper {

    FieldMapper INSTANCE = Mappers.getMapper(FieldMapper.class);

    FieldDTO toDTO(Field field);

    Field toEntity(FieldDTO fieldDTO);
    
    List<FieldDTO> toDTOList(List<Field> fields);
}