package systems.citronix.demo.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import systems.citronix.demo.Service.HarvestService;
import systems.citronix.demo.dto.request.HarvestRequestDTO;
import systems.citronix.demo.dto.request.TreeHarvestDetailDTO;
import systems.citronix.demo.dto.response.HarvestResponseDTO;
import systems.citronix.demo.exception.CustomNotFoundException;
import systems.citronix.demo.exception.ValidationException;
import systems.citronix.demo.mapper.HarvestMapper;
import systems.citronix.demo.model.Field;
import systems.citronix.demo.model.Harvest;
import systems.citronix.demo.model.Tree;
import systems.citronix.demo.model.TreeHarvestDetail;
import systems.citronix.demo.repository.FieldRepository;
import systems.citronix.demo.repository.HarvestRepository;
import systems.citronix.demo.repository.TreeRepository;

@Service
public class HarvestServiceImpl implements HarvestService {

    @Autowired
    private HarvestRepository harvestRepository;

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private TreeRepository treeRepository;

    @Autowired
    private HarvestMapper HarvestMapper;

    @Transactional
    public HarvestResponseDTO createHarvest(HarvestRequestDTO harvestDTO) {
        Field field = fieldRepository.findById(harvestDTO.fieldId())
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + harvestDTO.fieldId()));

        if (harvestRepository.existsBySeasonAndFieldId(harvestDTO.season(), harvestDTO.fieldId())) {
            throw new IllegalArgumentException("A harvest already exists for this season and field.");
        }

        List<Tree> trees = treeRepository.findByFieldId(field.getId());
        if (trees.isEmpty()) {
            throw new ValidationException("No trees found for the specified field (ID: " + harvestDTO.fieldId() + ").");
        }

        Harvest harvest = HarvestMapper.toEntity(harvestDTO);
        harvest.setField(field);

        double totalQuantity = 0.0;

        if (harvest.getTreeHarvestDetails() != null) {
            for (TreeHarvestDetail detail : harvest.getTreeHarvestDetails()) {
                Tree tree = treeRepository.findById(detail.getTree().getId())
                        .orElseThrow(() -> new CustomNotFoundException(
                                "Tree not found with ID: " + detail.getTree().getId()));

                double treeProductivity = tree.calculateProductivity();
                detail.setTree(tree);
                detail.setHarvest(harvest);
                detail.setQuantity(treeProductivity);

                totalQuantity += treeProductivity;
            }
        }

        harvest.setQuantity(totalQuantity);
        Harvest savedHarvest = harvestRepository.save(harvest);

        return HarvestMapper.toResponseDTO(savedHarvest);
    }

    @Transactional
    @Override
    public HarvestResponseDTO updateHarvest(Long id, HarvestRequestDTO harvestDTO) {
        Harvest existingHarvest = harvestRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Harvest not found with ID: " + id));

        Field field = fieldRepository.findById(harvestDTO.fieldId())
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + harvestDTO.fieldId()));

        existingHarvest.setSeason(harvestDTO.season());
        existingHarvest.setDate(harvestDTO.date());
        existingHarvest.setField(field);

        double totalQuantity = 0.0;
        existingHarvest.getTreeHarvestDetails().clear();
        for (TreeHarvestDetailDTO detailDTO : harvestDTO.treeHarvestDetails()) {
            Long treeId = detailDTO.treeId();
            Tree tree = treeRepository.findById(treeId)
                    .orElseThrow(() -> new ValidationException("Tree with ID " + treeId + " does not exist."));

            if (!tree.getField().getId().equals(harvestDTO.fieldId())) {
                throw new ValidationException(
                        "Tree with ID " + treeId + " does not belong to the specified field (ID: "
                                + harvestDTO.fieldId() + ").");
            }

            TreeHarvestDetail detail = new TreeHarvestDetail();
            detail.setTree(tree);
            detail.setHarvest(existingHarvest);

            double treeProductivity = tree.calculateProductivity();
            detail.setQuantity(treeProductivity);

            totalQuantity += treeProductivity;
            existingHarvest.getTreeHarvestDetails().add(detail);
        }

        existingHarvest.setQuantity(totalQuantity);
        Harvest updatedHarvest = harvestRepository.save(existingHarvest);

        return HarvestMapper.toResponseDTO(updatedHarvest);
    }

    @Transactional
    @Override
    public HarvestResponseDTO deleteHarvest(Long id) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Harvest not found with ID: " + id));

        harvestRepository.delete(harvest);

        return HarvestMapper.toResponseDTO(harvest);
    }

    public List<HarvestResponseDTO> getHarvests() {
        return harvestRepository.findAll().stream()
                .map(HarvestMapper::toResponseDTO)
                .toList();
    }

}
