package com.citronix.demo.service;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.citronix.demo.Service.FieldService;
import com.citronix.demo.dto.FieldDTO;
import com.citronix.demo.model.Farm;
import com.citronix.demo.repository.FarmRepository;
import com.citronix.demo.repository.FieldRepository;

import static org.mockito.Mockito.*;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class FieldServiceTest {

    @InjectMocks
    private FieldService fieldService;

    @Mock
    private FieldRepository fieldRepository;

    @Mock
    private FarmRepository farmRepository;

    // @Test
    // void testCreateFieldWithRecordDTO() {
    //     Farm farm = new Farm();
    //     farm.setSurface(10);

    //     when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));
    //     when(fieldRepository.findAllByFarmId(1L)).thenReturn(List.of(new Field(1L, "Field1", 5, farm)));

    //     FieldDTO fieldDTO = new FieldDTO(null, "Field2", 4.5, 1L);
    //     Field createdField = new Field(2L, "Field2", 4.5, farm);

    //     when(fieldRepository.save(any(Field.class))).thenReturn(createdField);

    //     Field result = fieldService.createField(fieldDTO);

    //     assertEquals("Field2", result.getName());
    //     assertEquals(4.5, result.getSurface());
    // }
}
