package com.citronix.demo.Service;

import java.util.List;

import com.citronix.demo.dto.FarmDTO;
import com.citronix.demo.dto.FarmSearchCriteria;
import com.citronix.demo.model.Farm;

public interface FarmService {

    Farm createFarm(FarmDTO farmDTO);

    List<Farm> getAllFarms();
    Farm getFarmById(Long id);
    Farm updateFarm(Long id, FarmDTO farmDTO);
    void deleteFarm(Long id);
    List<FarmDTO> searchFarms(FarmSearchCriteria criteria);

    
} 