package systems.citronix.demo.dto.response;

public record FieldResponseDTO(
    Long id,
    String name,
    Double surface,
    FarmResponseDTO farm 
) {}