package systems.citronix.demo.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import systems.citronix.demo.dto.embedded.FieldDTO;
import systems.citronix.demo.dto.request.FarmRequestDTO;
import systems.citronix.demo.dto.response.FarmResponseDTO;
import systems.citronix.demo.model.Farm;
import systems.citronix.demo.model.Field;

@Mapper(componentModel = "spring")
public interface FarmMapper {

    FarmResponseDTO toResponseDTO(Farm farm);

    Farm toEntity(FarmRequestDTO farmRequestDTO);

    List<FieldDTO> toFieldDTOList(List<Field> fields);
}