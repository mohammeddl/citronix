package com.citronix.demo.Service;

import java.util.List;

import com.citronix.demo.dto.FarmDTO;
import com.citronix.demo.dto.FarmSearchCriteria;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FarmService {

    FarmDTO createFarm(FarmDTO farmDTO);

    Page<FarmDTO> getAllFarms(Pageable pageable);

    FarmDTO getFarmById(Long id);

    FarmDTO updateFarm(Long id, FarmDTO farmDTO);

    void deleteFarm(Long id);

    List<FarmDTO> searchFarms(FarmSearchCriteria criteria);

}