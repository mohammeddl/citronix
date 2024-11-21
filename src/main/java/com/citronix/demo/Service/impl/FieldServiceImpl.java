package com.citronix.demo.Service.impl;

import java.util.List;

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

import jakarta.transaction.Transactional;

@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private FarmRepository farmRepository;

    @Transactional
    public FieldDTO createField(FieldDTO fieldDTO) {
        Farm farm = farmRepository.findById(fieldDTO.farmId())
                .orElseThrow(() -> new CustomNotFoundException("Farm not found with ID: " + fieldDTO.farmId()));

        validateFieldConstraints(farm, fieldDTO);
        Field field = FieldMapper.INSTANCE.toEntity(fieldDTO);
        field.setFarm(farm);
        Field savedField = fieldRepository.save(field);
        return FieldMapper.INSTANCE.toDTO(savedField);
    }

    @Override
    public List<FieldDTO> getAllFields() {
        return fieldRepository.findAll().stream()
                .map(FieldMapper.INSTANCE::toDTO)
                .toList();
    }

    public FieldDTO getFieldById(Long id) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + id));
        return FieldMapper.INSTANCE.toDTO(field);
    }

    @Transactional
    public FieldDTO updateField(Long id, FieldDTO fieldDTO) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + id));

        Farm farm = farmRepository.findById(fieldDTO.farmId())
                .orElseThrow(() -> new CustomNotFoundException("Farm not found with ID: " + fieldDTO.farmId()));

        validateFieldConstraints(farm, fieldDTO);
        field.setName(fieldDTO.name());
        field.setSurface(fieldDTO.surface());
        field.setFarm(farm);

        Field updatedField = fieldRepository.save(field);
        return FieldMapper.INSTANCE.toDTO(updatedField);
    }

    @Transactional
    @Override
    public FieldDTO deleteField(Long id) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + id));
        fieldRepository.delete(field);
        return FieldMapper.INSTANCE.toDTO(field);
    }

    private void validateFieldConstraints(Farm farm, FieldDTO fieldDTO) {
        if (fieldDTO.surface() < 0.1) {
            throw new ValidationException("The field surface must be at least 0.1 hectares.");
        }

        double maxFieldSurface = farm.getSurface() * 0.5;
        if (fieldDTO.surface() > maxFieldSurface) {
            throw new ValidationException("The field surface cannot exceed 50% of the farm's total surface.");
        }

        long fieldCount = fieldRepository.countByFarmId(farm.getId());
        if (fieldCount >= 10) {
            throw new ValidationException("A farm cannot contain more than 10 fields.");
        }
    }

}
