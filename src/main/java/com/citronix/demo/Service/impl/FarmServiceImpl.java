package com.citronix.demo.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citronix.demo.Service.FarmService;
import com.citronix.demo.Service.FieldService;
import com.citronix.demo.dto.FarmDTO;
import com.citronix.demo.dto.FarmSearchCriteria;
import com.citronix.demo.exception.CustomNotFoundException;
import com.citronix.demo.mapper.FarmMapper;
import com.citronix.demo.model.Farm;
import com.citronix.demo.repository.FarmRepository;
import com.citronix.demo.repository.FieldRepository;

import jakarta.transaction.Transactional;

@Service
public class FarmServiceImpl implements FarmService {

    @Autowired
    private FarmRepository farmRepository;
    @Autowired
    private FieldRepository fieldRepository;

    @Transactional
    public Farm createFarm(FarmDTO farmDTO) {
        if (fieldRepository.existsByFarmId(farmDTO.id())) {
            throw new IllegalArgumentException("A field already exists for this farm.");
        }

        Farm farm = Farm.builder()
                .name(farmDTO.name())
                .localization(farmDTO.localization())
                .surface(farmDTO.surface())
                .creationDate(farmDTO.creationDate())
                .build();

        return farmRepository.save(farm);
    }

    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    public Farm getFarmById(Long id) {
        return farmRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Farm not found with ID: " + id));
    }

    @Transactional
    public Farm updateFarm(Long id, FarmDTO farmDTO) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Farm not found with ID: " + id));

        farm.setName(farmDTO.name());
        farm.setLocalization(farmDTO.localization());
        farm.setSurface(farmDTO.surface());
        farm.setCreationDate(farmDTO.creationDate());

        return farmRepository.save(farm);
    }

    @Transactional
    public void deleteFarm(Long id) {
        farmRepository.deleteById(id);
    }

    public List<FarmDTO> searchFarms(FarmSearchCriteria criteria) {
        return farmRepository.searchFarm(criteria).stream()
                .map(FarmMapper.INSTANCE::toDTO)
                .toList();
    }

}
