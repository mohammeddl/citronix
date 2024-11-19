package com.citronix.demo.Service;

import java.util.List;

import com.citronix.demo.dto.HarvestDTO;

public interface HarvestService {
    HarvestDTO createHarvest(HarvestDTO harvestDTO) ;
    List<HarvestDTO> getHarvests();
    
}