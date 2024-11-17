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

@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private FarmRepository farmRepository;

    public FieldDTO createField(FieldDTO fieldDTO) {
        Farm farm = farmRepository.findById(fieldDTO.farmId())
                .orElseThrow(() -> new CustomNotFoundException("Farm not found with ID: " + fieldDTO.farmId()));
        Field field = FieldMapper.INSTANCE.toEntity(fieldDTO);
        field.setFarm(farm);
        Field savedField = fieldRepository.save(field);
        return FieldMapper.INSTANCE.toDTO(savedField);
    }

    public List<FieldDTO> getAllFields() {
        List<Field> fields = fieldRepository.findAll();
        return FieldMapper.INSTANCE.toDTOList(fields);
    }

    public FieldDTO getFieldById(Long id) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + id));
        return FieldMapper.INSTANCE.toDTO(field);
    }

    public FieldDTO updateField(Long id, FieldDTO fieldDTO) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + id));
        if (fieldDTO.farmId() != null) {
            Farm farm = farmRepository.findById(fieldDTO.farmId())
                    .orElseThrow(() -> new CustomNotFoundException("Farm not found with ID: " + fieldDTO.farmId()));
            field.setFarm(farm);
        }
        field.setName(fieldDTO.name());
        field.setSurface(fieldDTO.surface());
        Field savedField = fieldRepository.save(field);
        return FieldMapper.INSTANCE.toDTO(savedField);
    }

    public void deleteField(Long id) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + id));
        fieldRepository.delete(field);
    }

    public void validateFieldDTO(FieldDTO fieldDTO) {
        if (fieldDTO.name() == null || fieldDTO.name().isEmpty()) {
            throw new ValidationException("Field name is required");
        }
        if (fieldDTO.farmId() == null) {
            throw new ValidationException("Farm ID is required");
        }
    }
}
