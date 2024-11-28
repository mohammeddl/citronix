package systems.citronix.demo.dto.response;

public record HarvestResponseDTO(
    Long id,
    String season,
    Double quantity,
    FieldResponseDTO field,  
    TreeResponseDTO tree     
) {}