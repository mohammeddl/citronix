package systems.citronix.demo.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import systems.citronix.demo.validation.ValidPlantingDate;

public record TreeRequestDTO(
        @ValidPlantingDate @NotNull(message = "Planting date is required") LocalDate plantingDate,
        @NotNull(message = "Field ID is required") Long fieldId) {
}