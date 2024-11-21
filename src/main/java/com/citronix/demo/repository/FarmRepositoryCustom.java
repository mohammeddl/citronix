package com.citronix.demo.repository;

import java.util.List;

import com.citronix.demo.dto.FarmSearchCriteria;
import com.citronix.demo.model.Farm;

public interface FarmRepositoryCustom {
    
    List<Farm> searchFarm(FarmSearchCriteria farmSearchCriteria);
}
