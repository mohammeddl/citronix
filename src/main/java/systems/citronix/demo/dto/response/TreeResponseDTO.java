package systems.citronix.demo.dto.response;

import java.time.LocalDate;

public record TreeResponseDTO(
    Long id,
    LocalDate plantingDate,
    FieldResponseDTO field  
) {}