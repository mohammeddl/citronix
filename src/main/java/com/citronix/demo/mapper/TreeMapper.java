package com.citronix.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.citronix.demo.dto.TreeDTO;
import com.citronix.demo.model.Tree;

@Mapper(componentModel = "spring")
public class TreeMapper {
    TreeMapper INSTANCE = Mappers.getMapper(TreeMapper.class);

    @Mapping(source = "field.id", target = "fieldId")
    TreeDTO toDTO(Tree tree);

    @Mapping(source = "fieldId", target = "field.id")
    Tree toEntity(TreeDTO treeDTO);
    
}
