package com.citronix.demo.Service;

import java.util.List;

import com.citronix.demo.dto.FieldDTO;
import com.citronix.demo.model.Field;

public interface FieldService {
    FieldDTO createField(FieldDTO fieldDTO);
    FieldDTO getFieldById(Long id);
    FieldDTO updateField(Long id, FieldDTO fieldDTO);
    void deleteField(Long id);
    List<FieldDTO> getAllFields();


    
} 
