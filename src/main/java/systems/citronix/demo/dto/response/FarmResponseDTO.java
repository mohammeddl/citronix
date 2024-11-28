package systems.citronix.demo.dto.response;

import java.util.List;

import systems.citronix.demo.dto.embedded.FieldDTO;

public record FarmResponseDTO(
    Long id,
    String name,
    String localization,
    Double surface,
    List<FieldDTO> fields
) {}
