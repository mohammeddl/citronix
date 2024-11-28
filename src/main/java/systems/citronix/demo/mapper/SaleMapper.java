package systems.citronix.demo.mapper;

import org.mapstruct.Mapper;

import systems.citronix.demo.dto.request.SaleRequestDTO;
import systems.citronix.demo.dto.response.SaleResponseDTO;
import systems.citronix.demo.model.Sale;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    SaleResponseDTO toResponseDTO(Sale sale);

    Sale toEntity(SaleRequestDTO saleRequestDTO);

}
