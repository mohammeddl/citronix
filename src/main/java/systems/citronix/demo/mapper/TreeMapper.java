package systems.citronix.demo.mapper;

import org.mapstruct.Mapper;

import systems.citronix.demo.dto.request.TreeRequestDTO;
import systems.citronix.demo.dto.response.TreeResponseDTO;
import systems.citronix.demo.model.Tree;

@Mapper(componentModel = "spring")
public interface TreeMapper {

    TreeResponseDTO toResponseDTO(Tree tree);

    Tree toEntity(TreeRequestDTO treeRequestDTO);

}