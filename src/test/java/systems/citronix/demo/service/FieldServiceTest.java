package systems.citronix.demo.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import systems.citronix.demo.Service.impl.FieldServiceImpl;
import systems.citronix.demo.dto.request.FieldRequestDTO;
import systems.citronix.demo.dto.response.FieldResponseDTO;
import systems.citronix.demo.exception.CustomNotFoundException;
import systems.citronix.demo.exception.ValidationException;
import systems.citronix.demo.mapper.FieldMapper;
import systems.citronix.demo.model.Farm;
import systems.citronix.demo.model.Field;
import systems.citronix.demo.repository.FarmRepository;
import systems.citronix.demo.repository.FieldRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FieldServiceTest {

    @InjectMocks
    private FieldServiceImpl fieldService;

    @Mock
    private FieldRepository fieldRepository;

    @Mock
    private FarmRepository farmRepository;

    @Mock
    private FieldMapper fieldMapper;

    private Farm farm;
    private Field field;
    private FieldResponseDTO fieldDTO;

    // @BeforeEach
    // void setUp() {
    //     farm = new Farm();
    //     farm.setId(1L);
    //     farm.setName("Test Farm");
    //     farm.setLocalization("Test Location");
    //     farm.setSurface(100.0);
    //     farm.setCreationDate(LocalDate.of(2024, 1, 1));

    //     field = new Field();
    //     field.setId(1L);
    //     field.setName("Test Field");
    //     field.setSurface(5.0);
    //     field.setFarm(farm);

    //     fieldDTO = new FieldRequestDTO(1L, "Test Field", 5.0, 1L);
    // }

    // @Test
    // void testCreateField_Success() {
    //     lenient().when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));
    //     lenient().when(fieldMapper.toEntity(fieldDTO)).thenReturn(field);
    //     lenient().when(fieldRepository.save(field)).thenReturn(field);
    //     lenient().when(fieldMapper.toDTO(field)).thenReturn(fieldDTO);

    //     FieldResponseDTO result = fieldService.createField(fieldDTO);

    //     assertEquals(fieldDTO, result);
    // }

    // @Test
    // void testCreateField_BelowMinimumSurface() {
    //     FieldResponseDTO invalidFieldDTO = new FieldReestDTO(null, "Small Field", 0.05, 1L);

    //     when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));

    //     ValidationException exception = assertThrows(
    //             ValidationException.class,
    //             () -> fieldService.createField(invalidFieldDTO));

    //     assertEquals("The field surface must be at least 0.1 hectares.", exception.getMessage());
    //     verify(fieldRepository, never()).save(any(Field.class));
    // }

    // @Test
    // void testCreateField_ExceedsMaximumSurface() {
    //     FieldResponseDTO invalidFieldDTO = new FieldReestDTO(null, "Large Field", 60.0, 1L);

    //     when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));

    //     ValidationException exception = assertThrows(
    //             ValidationException.class,
    //             () -> fieldService.createField(invalidFieldDTO));

    //     assertEquals("The field surface cannot exceed 50% of the farm's total surface.", exception.getMessage());
    //     verify(fieldRepository, never()).save(any(Field.class));
    // }

    // @Test
    // void testCreateField_FarmNotFound() {
    //     when(farmRepository.findById(99L)).thenReturn(Optional.empty());

    //     FieldResponseDTO fieldDTO = new FieldReestDTO(null, "Invalid Field", 5.0, 99L);

    //     CustomNotFoundException exception = assertThrows(
    //             CustomNotFoundException.class,
    //             () -> fieldService.createField(fieldDTO));

    //     assertEquals("Farm not found with ID: 99", exception.getMessage());
    //     verify(fieldRepository, never()).save(any(Field.class));
    // }

    // @Test
    // void testGetAllFields_Success() {
    //     lenient().when(fieldRepository.findAll()).thenReturn(List.of(field));
    //     lenient().when(fieldMapper.toDTOList(anyList())).thenReturn(List.of(fieldDTO));

    //     List<FieldResponseDTO> result = fieldService.getAllFields();

    //     assertNotNull(result);
    //     assertEquals(1, result.size());
    //     assertEquals("Test Field", result.get(0).name());
    //     verify(fieldRepository, times(1)).findAll();
    // }

    // @Test
    // void testGetFieldById_Success() {
    //     lenient().when(fieldRepository.findById(1L)).thenReturn(Optional.of(field));
    //     lenient().when(fieldMapper.toResponseDTO(field)).thenReturn(fieldDTO);

    //     FieldResponseDTO result = fieldService.getFieldById(1L);

    //     assertNotNull(result);
    //     assertEquals(1L, result.id());
    //     verify(fieldRepository, times(1)).findById(1L);
    // }

    // @Test
    // void testGetFieldById_NotFound() {
    //     when(fieldRepository.findById(99L)).thenReturn(Optional.empty());

    //     assertThrows(CustomNotFoundException.class, () -> fieldService.getFieldById(99L));
    //     verify(fieldRepository, times(1)).findById(99L);
    // }

    // @Test
    // void testDeleteField_Success() {
    //     when(fieldRepository.findById(1L)).thenReturn(Optional.of(field));

    //     fieldService.deleteField(1L);

    //     verify(fieldRepository, times(1)).findById(1L);
    //     verify(fieldRepository, times(1)).delete(field);
    // }

    // @Test
    // void testDeleteField_NotFound() {
    //     when(fieldRepository.findById(99L)).thenReturn(Optional.empty());

    //     assertThrows(CustomNotFoundException.class, () -> fieldService.deleteField(99L));
    //     verify(fieldRepository, times(1)).findById(99L);
    //     verify(fieldRepository, never()).delete(any(Field.class));
    // }
}
