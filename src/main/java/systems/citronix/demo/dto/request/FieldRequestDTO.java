package systems.citronix.demo.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public record FieldRequestDTO(
        @NotBlank(message = "name is required") String name,
        @DecimalMin(value = "0.1", message = "Field surface must be at least 0.1 hectares") double surface,
        Long farmId) {
}