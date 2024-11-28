package systems.citronix.demo.Service;

import java.util.List;

import systems.citronix.demo.dto.request.HarvestRequestDTO;
import systems.citronix.demo.dto.response.HarvestResponseDTO;

public interface HarvestService {
    HarvestResponseDTO createHarvest(HarvestRequestDTO harvestDTO);
    List<HarvestResponseDTO> getHarvests();
    HarvestResponseDTO updateHarvest(Long id, HarvestRequestDTO harvestDTO);
    HarvestResponseDTO deleteHarvest(Long id);
    
}