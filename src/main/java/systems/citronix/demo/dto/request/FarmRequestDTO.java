package systems.citronix.demo.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public record FarmRequestDTO(
        @NotBlank(message = "Farm name cannot be blank") String name,
        @NotBlank(message = "Localization cannot be blank") String localization,
        @DecimalMin(value = "1.0", message = "Farm surface must be greater than 1.0 hectares") double surface,
        @PastOrPresent(message = "Creation date must be in the past or present") LocalDate creationDate) {
}