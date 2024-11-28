package systems.citronix.demo.dto.request;

import jakarta.validation.constraints.NotNull;

public record TreeHarvestDetailDTO(
        @NotNull(message = "Tree ID is required") Long treeId) {
}