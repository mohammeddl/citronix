package com.citronix.demo.Service;

import java.util.List;

import com.citronix.demo.dto.FieldDTO;

public interface FieldService {
    FieldDTO createField(FieldDTO fieldDTO);
    FieldDTO getFieldById(Long id);
    FieldDTO updateField(Long id, FieldDTO fieldDTO);
    FieldDTO deleteField(Long id);
    List<FieldDTO> getAllFields();


    
} 
