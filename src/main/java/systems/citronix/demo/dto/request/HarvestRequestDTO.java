package systems.citronix.demo.dto.request;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import systems.citronix.demo.model.Season;

public record HarvestRequestDTO(
        @NotNull(message = "Season is required") Season season,
        @NotNull(message = "Harvest date is required") LocalDate date,
        @NotNull(message = "Field ID is required") Long fieldId,
        @Valid List<TreeHarvestDetailDTO> treeHarvestDetails) {
}
