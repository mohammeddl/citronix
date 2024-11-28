package systems.citronix.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import systems.citronix.demo.Service.impl.HarvestServiceImpl;
import systems.citronix.demo.dto.request.HarvestRequestDTO;
import systems.citronix.demo.exception.CustomNotFoundException;
import systems.citronix.demo.exception.ValidationException;
import systems.citronix.demo.mapper.HarvestMapper;
import systems.citronix.demo.model.Field;
import systems.citronix.demo.model.Harvest;
import systems.citronix.demo.model.Season;
import systems.citronix.demo.model.Tree;
import systems.citronix.demo.repository.FieldRepository;
import systems.citronix.demo.repository.HarvestRepository;
import systems.citronix.demo.repository.TreeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class HarvestServiceImplTest {

    @Mock
    private HarvestRepository harvestRepository;

    @Mock
    private FieldRepository fieldRepository;

    @Mock
    private TreeRepository treeRepository;

    @Mock
    private HarvestMapper harvestMapper;

    @InjectMocks
    private HarvestServiceImpl harvestService;

    private Field field;
    private Tree tree;
    private Harvest harvest;

    @BeforeEach
    void setUp() {
        field = new Field();
        field.setId(1L);
        field.setName("Test Field");
        field.setSurface(5.0);

        tree = new Tree();
        tree.setId(1L);
        tree.setField(field);

        harvest = new Harvest();
        harvest.setId(1L);
        harvest.setSeason(Season.SPRING);
        harvest.setDate(LocalDate.of(2024, 12, 12));
        harvest.setField(field);
    }

    // @Test
    // void testCreateHarvest_Success() {
    //     when(fieldRepository.findById(1L)).thenReturn(Optional.of(field));
    //     when(treeRepository.findByFieldId(1L)).thenReturn(List.of(tree));
    //     when(harvestRepository.save(any(Harvest.class))).thenReturn(harvest);

    //     HarvestRequestDTO request = new HarvestRequestDTO(null, Season.SPRING, LocalDate.of(2024, 12, 12), 0, 1L, null);
    //     HarvestRequestDTO response = harvestService.createHarvest(request);

    //     assertNotNull(response);
    //     assertEquals(1L, response.id());
    //     assertEquals(Season.SPRING, response.season());
    //     verify(harvestRepository, times(1)).save(any(Harvest.class));
    // }

    // @Test
    // void testCreateHarvest_FieldNotFound() {
    //     when(fieldRepository.findById(99L)).thenReturn(Optional.empty());

    //     HarvestRequestDTO request = new HarvestRequestDTO(null, Season.SPRING, LocalDate.of(2024, 12, 12), 0, 99L, null);

    //     assertThrows(CustomNotFoundException.class, () -> harvestService.createHarvest(request));
    //     verify(harvestRepository, never()).save(any(Harvest.class));
    // }

    // @Test
    // void testCreateHarvest_NoTreesInField() {
    //     when(fieldRepository.findById(1L)).thenReturn(Optional.of(field));
    //     when(treeRepository.findByFieldId(1L)).thenReturn(List.of());

    //     HarvestRequestDTO request = new HarvestRequestDTO(null, Season.SPRING, LocalDate.of(2024, 12, 12), 0, 1L, null);

    //     assertThrows(ValidationException.class, () -> harvestService.createHarvest(request));
    //     verify(harvestRepository, never()).save(any(Harvest.class));
    // }

    // @Test
    // void testGetHarvests_Success() {
    //     when(harvestRepository.findAll()).thenReturn(List.of(harvest));
    //     List<HarvestRequestDTO> harvests = harvestService.getHarvests();

    //     assertNotNull(harvests);
    //     assertEquals(1, harvests.size());
    //     assertEquals(1L, harvests.get(0).id());
    //     verify(harvestRepository, times(1)).findAll();
    // }
}
