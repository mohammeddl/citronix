package systems.citronix.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import systems.citronix.demo.Service.impl.FarmServiceImpl;
import systems.citronix.demo.dto.embedded.FieldDTO;
import systems.citronix.demo.dto.request.FarmRequestDTO;
import systems.citronix.demo.dto.response.FarmResponseDTO;
import systems.citronix.demo.exception.CustomNotFoundException;
import systems.citronix.demo.mapper.FarmMapper;
import systems.citronix.demo.mapper.FieldMapper;
import systems.citronix.demo.model.Farm;
import systems.citronix.demo.repository.FarmRepository;

import java.time.LocalDate;
import java.util.List;
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

    @Mock
    private FieldMapper fieldMapper;

    private Farm farm;
    private FarmRequestDTO farmDTO;
    private FarmRequestDTO farmDTO1;

    @BeforeEach
    void setUp() {
        farm = new Farm();
        farm.setId(1L);
        farm.setName("Test Farm");
        farm.setLocalization("Test Location");
        farm.setSurface(100.0);
        farm.setCreationDate(LocalDate.of(2024, 1, 1));

        farmDTO = new FarmRequestDTO(
                "Test Farm",
                "Test Location",
                100.0,
                LocalDate.of(2024, 1, 1));

        farmDTO1 = new FarmRequestDTO(
                "Test Farm",
                "Test Location",
                100.0,
                LocalDate.of(2024, 1, 1));
    }

    // @Test
    // void testCreateFarm_Success() {
    //     FarmRequestDTO farmDTO = new FarmRequestDTO(farm.getName(), farm.getLocalization(), farm.getSurface(),
    //             farm.getCreationDate());

    //     List<FieldDTO> farmDTOList = fieldMapper.toResponseDTO(List.of(new FieldDTO(1L, "field b", 49), new FieldDTO(1L, "field b", 49)));
    //     FarmResponseDTO farmResponseDTO = new FarmResponseDTO(farm.getId(), farm.getName(), farm.getLocalization(),
    //             farm.getSurface(), farmDTOList);
    //     lenient().when(farmMapper.toResponseDTO(farm)).thenReturn(farmResponseDTO);
    //     when(farmRepository.save(farm)).thenReturn(farm);

    //     FarmResponseDTO savedFarmDTO = farmServiceImpl.createFarm(farmDTO);

    //     assertNotNull(savedFarmDTO);
    //     assertEquals(farmDTO, savedFarmDTO);
    // }

    // @Test
    // void testGetFarmById_Success() {
    //     when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));
    //     lenient().when(farmMapper.toResponseDTO(farm)).thenReturn(new FarmResponseDTO(farm.getId(), farm.getName(),
    //             farm.getLocalization(), farm.getSurface(), farm.getCreationDate(), "ValidValue"));

    //     FarmResponseDTO result = farmServiceImpl.getFarmById(1L);

    //     assertNotNull(result);
    //     assertEquals(1L, result.id());
    //     assertEquals("Test Farm", result.name());
    //     verify(farmRepository, times(1)).findById(1L);
    // }

    // @Test
    // void testGetFarmById_NotFound() {
    //     when(farmRepository.findById(99L)).thenReturn(Optional.empty());

    //     assertThrows(CustomNotFoundException.class, () -> farmServiceImpl.getFarmById(99L));
    //     verify(farmRepository, times(1)).findById(99L);
    // }

    // @Test
    // void testDeleteFarm_NotFound() {
    //     when(farmRepository.existsById(89L)).thenReturn(false);

    //     assertThrows(CustomNotFoundException.class, () -> farmServiceImpl.deleteFarm(89L));
    //     verify(farmRepository, times(1)).existsById(89L);
    // }

}
