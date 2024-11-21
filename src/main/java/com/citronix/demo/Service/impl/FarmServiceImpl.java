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
    public FarmDTO createFarm(FarmDTO farmDTO) {
        Farm farm = FarmMapper.INSTANCE.toEntity(farmDTO);
        Farm savedFarm = farmRepository.save(farm);
        return FarmMapper.INSTANCE.toDTO(savedFarm);
    }

    public List<FarmDTO> getAllFarms() {
        return farmRepository.findAll().stream()
                .map(FarmMapper.INSTANCE::toDTO)
                .toList();
    }

    public FarmDTO getFarmById(Long id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Farm not found with ID: " + id));
        return FarmMapper.INSTANCE.toDTO(farm);
    }

    @Transactional
    public FarmDTO updateFarm(Long id, FarmDTO farmDTO) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Farm not found with ID: " + id));

        farm.setName(farmDTO.name());
        farm.setLocalization(farmDTO.localization());
        farm.setSurface(farmDTO.surface());
        farm.setCreationDate(farmDTO.creationDate());

        Farm updatedFarm = farmRepository.save(farm);
        return FarmMapper.INSTANCE.toDTO(updatedFarm);
    }

    @Transactional
    public void deleteFarm(Long id) {
        if (!farmRepository.existsById(id)) {
            throw new CustomNotFoundException("Farm not found with ID: " + id);
        }
        farmRepository.deleteById(id);
    }

    public List<FarmDTO> searchFarms(FarmSearchCriteria criteria) {
        return farmRepository.searchFarm(criteria).stream()
                .map(FarmMapper.INSTANCE::toDTO)
                .toList();
    }

}
