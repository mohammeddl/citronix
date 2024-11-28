package systems.citronix.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import systems.citronix.demo.dto.request.FieldRequestDTO;
import systems.citronix.demo.dto.response.FieldResponseDTO;
import systems.citronix.demo.model.Field;

@Mapper(componentModel = "spring")
public interface FieldMapper {

    FieldResponseDTO toResponseDTO(Field field);

    Field toEntity(FieldRequestDTO fieldRequestDTO);

    List<FieldResponseDTO> toResponseDTOList(List<Field> fields);
}