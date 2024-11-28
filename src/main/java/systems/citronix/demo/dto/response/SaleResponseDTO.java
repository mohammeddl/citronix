package systems.citronix.demo.dto.response;

import java.time.LocalDate;

public record SaleResponseDTO(
        Long id,
        String buyer,
        Double amount,
        LocalDate saleDate,
        FieldResponseDTO field) {
}