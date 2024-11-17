package com.citronix.demo.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citronix.demo.Service.FieldService;
import com.citronix.demo.dto.FieldDTO;
import com.citronix.demo.exception.ValidationException;
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

    public Field createField(FieldDTO fieldDTO) {
        Farm farm = farmRepository.findById(fieldDTO.farmId())
                .orElseThrow(() -> new IllegalArgumentException("Farm not found"));

        double totalFieldArea = fieldRepository.findAllByFarmId(farm.getId())
                .stream()
                .mapToDouble(Field::getSurface)
                .sum();

        if (fieldDTO.surface() > (farm.getSurface() * 0.5)) {
            throw new ValidationException("Field area exceeds 50% of farm area");
        }

        if ((totalFieldArea + fieldDTO.surface()) >= farm.getSurface()) {
            throw new ValidationException("Total field areas cannot exceed farm area");
        }

        if (fieldRepository.countByFarmId(farm.getId()) >= 10) {
            throw new IllegalArgumentException("Farm cannot have more than 10 fields");
        }

        Field field = Field.builder()
                .name(fieldDTO.name())
                .surface(fieldDTO.surface())
                .farm(farm)
                .build();

        return fieldRepository.save(field);
    }
}
