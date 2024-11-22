package com.citronix.demo.service;

import com.citronix.demo.Service.impl.FarmServiceImpl;
import com.citronix.demo.dto.FarmDTO;
import com.citronix.demo.exception.CustomNotFoundException;
import com.citronix.demo.mapper.FarmMapper;
import com.citronix.demo.model.Farm;
import com.citronix.demo.repository.FarmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FarmServiceTest {

    @Mock
    private FarmRepository farmRepository;

    @InjectMocks
    private FarmServiceImpl farmServiceImpl;

    @Mock
    private FarmMapper farmMapper;

    private Farm farm;
    private FarmDTO farmDTO;
    @BeforeEach
    void setUp() {
        farm = new Farm();
        farm.setId(1L);
        farm.setName("Test Farm");
        farm.setLocalization("Test Location");
        farm.setSurface(100.0);
        farm.setCreationDate(LocalDate.of(2024, 1, 1));

        farmDTO = new FarmDTO(
                1L,
                "Test Farm",
                "Test Location",
                100.0,
                LocalDate.of(2024, 1, 1)
        );
    }


    @Test
    void testCreateFarm_Success() {
        FarmDTO farmDTO = new FarmDTO(farm.getId(), farm.getName(), farm.getLocalization(), farm.getSurface(), farm.getCreationDate());
        lenient().when(farmMapper.toDTO(farm)).thenReturn(farmDTO);
        when(farmRepository.save(farm)).thenReturn(farm);

        FarmDTO savedFarmDTO = farmServiceImpl.createFarm(farmDTO);

        assertNotNull(savedFarmDTO);
        assertEquals(farmDTO, savedFarmDTO);
    }

    

    @Test
    void testGetFarmById_Success() {
        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));
        lenient().when(farmMapper.toDTO(farm)).thenReturn(farmDTO);

        FarmDTO result = farmServiceImpl.getFarmById(1L);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Test Farm", result.name());
        verify(farmRepository, times(1)).findById(1L);
    }

    @Test
    void testGetFarmById_NotFound() {
        when(farmRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(CustomNotFoundException.class, () -> farmServiceImpl.getFarmById(99L));
        verify(farmRepository, times(1)).findById(99L);
    }

  @Test
void testDeleteFarm_NotFound() {
    when(farmRepository.existsById(89L)).thenReturn(false);

    assertThrows(CustomNotFoundException.class, () -> farmServiceImpl.deleteFarm(89L));
    verify(farmRepository, times(1)).existsById(89L);
}
    
}
