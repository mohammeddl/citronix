package com.citronix.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.citronix.demo.dto.HarvestDTO;
import com.citronix.demo.dto.TreeHarvestDetailDTO;
import com.citronix.demo.model.Harvest;
import com.citronix.demo.model.Tree;
import com.citronix.demo.model.TreeHarvestDetail;

@Mapper(componentModel = "spring")
public interface HarvestMapper {
    HarvestMapper INSTANCE = Mappers.getMapper(HarvestMapper.class);

    @Mapping(source = "field.id", target = "fieldId")
    HarvestDTO toDTO(Harvest harvest);

    @Mapping(source = "fieldId", target = "field.id")
    Harvest toEntity(HarvestDTO harvestDTO);

    // Map TreeHarvestDetailDTO to TreeHarvestDetail
    @Mapping(target = "tree", expression = "java(mapTree(detailDTO.treeId()))")
    TreeHarvestDetail toEntity(TreeHarvestDetailDTO detailDTO);

    // Helper method to map treeId to Tree
    default Tree mapTree(Long treeId) {
        if (treeId == null)
            return null;
        Tree tree = new Tree();
        tree.setId(treeId);
        return tree;
    }
}
