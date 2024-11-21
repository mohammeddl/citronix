package com.citronix.demo.Service;

import java.util.List;

import com.citronix.demo.dto.FarmDTO;
import com.citronix.demo.dto.FarmSearchCriteria;

public interface FarmService {

    FarmDTO createFarm(FarmDTO farmDTO);

    List<FarmDTO> getAllFarms();

    FarmDTO getFarmById(Long id);

    FarmDTO updateFarm(Long id, FarmDTO farmDTO);

    void deleteFarm(Long id);

    List<FarmDTO> searchFarms(FarmSearchCriteria criteria);

}