package systems.citronix.demo.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import systems.citronix.demo.dto.request.FarmRequestDTO;
import systems.citronix.demo.dto.request.FarmSearchCriteria;
import systems.citronix.demo.dto.response.FarmResponseDTO;

public interface FarmService {

    FarmResponseDTO createFarm(FarmRequestDTO farmDTO);

    Page<FarmResponseDTO> getAllFarms(Pageable pageable);

    FarmResponseDTO getFarmById(Long id);

    FarmResponseDTO updateFarm(Long id, FarmRequestDTO farmDTO);

    void deleteFarm(Long id);

    List<FarmResponseDTO> searchFarms(FarmSearchCriteria criteria);

}