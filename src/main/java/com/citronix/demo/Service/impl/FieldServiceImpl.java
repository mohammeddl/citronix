package com.citronix.demo.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citronix.demo.Service.FieldService;
import com.citronix.demo.dto.FieldDTO;
import com.citronix.demo.exception.CustomNotFoundException;
import com.citronix.demo.exception.ValidationException;
import com.citronix.demo.mapper.FieldMapper;
import com.citronix.demo.model.Farm;
import com.citronix.demo.model.Field;
import com.citronix.demo.repository.FarmRepository;
import com.citronix.demo.repository.FieldRepository;

@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private FarmRepository farmRepository;

    public FieldDTO createField(FieldDTO fieldDTO) {
        // Fetch the farm by ID
        Farm farm = farmRepository.findById(fieldDTO.farmId())
                .orElseThrow(() -> new CustomNotFoundException("Farm not found with ID: " + fieldDTO.farmId()));

        // Convert DTO to entity and set the farm
        Field field = FieldMapper.INSTANCE.toEntity(fieldDTO);
        field.setFarm(farm);

        // Save the field entity
        Field savedField = fieldRepository.save(field);

        // Convert entity back to DTO and return
        return FieldMapper.INSTANCE.toDTO(savedField);
    }
}
