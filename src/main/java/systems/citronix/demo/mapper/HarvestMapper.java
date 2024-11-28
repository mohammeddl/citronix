package systems.citronix.demo.mapper;

import java.lang.reflect.Field;

import org.mapstruct.Mapper;

import systems.citronix.demo.dto.request.HarvestRequestDTO;
import systems.citronix.demo.dto.response.FieldResponseDTO;
import systems.citronix.demo.dto.response.HarvestResponseDTO;
import systems.citronix.demo.dto.response.TreeResponseDTO;
import systems.citronix.demo.model.Harvest;
import systems.citronix.demo.model.Tree;

@Mapper(componentModel = "spring")
public interface HarvestMapper {

    HarvestResponseDTO toResponseDTO(Harvest harvest);

    Harvest toEntity(HarvestRequestDTO harvestRequestDTO);

}
