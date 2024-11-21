package com.citronix.demo.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citronix.demo.Service.HarvestService;
import com.citronix.demo.dto.HarvestDTO;
import com.citronix.demo.dto.TreeHarvestDetailDTO;
import com.citronix.demo.exception.CustomNotFoundException;
import com.citronix.demo.exception.ValidationException;
import com.citronix.demo.mapper.HarvestMapper;
import com.citronix.demo.model.Field;
import com.citronix.demo.model.Harvest;
import com.citronix.demo.model.Tree;
import com.citronix.demo.model.TreeHarvestDetail;
import com.citronix.demo.repository.FieldRepository;
import com.citronix.demo.repository.HarvestRepository;
import com.citronix.demo.repository.TreeRepository;

import jakarta.transaction.Transactional;

@Service
public class HarvestServiceImpl implements HarvestService {

    @Autowired
    private HarvestRepository harvestRepository;

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private TreeRepository treeRepository;

    @Transactional
    public HarvestDTO createHarvest(HarvestDTO harvestDTO) {
        Field field = fieldRepository.findById(harvestDTO.fieldId())
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + harvestDTO.fieldId()));

        if (harvestRepository.existsBySeasonAndFieldId(harvestDTO.season(), harvestDTO.fieldId())) {
            throw new IllegalArgumentException("A harvest already exists for this season and field.");
        }

        List<Tree> trees = treeRepository.findByFieldId(field.getId());
        if (trees.isEmpty()) {
            throw new ValidationException("No trees found for the specified field (ID: " + harvestDTO.fieldId() + ").");
        }

        Harvest harvest = HarvestMapper.INSTANCE.toEntity(harvestDTO);
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

        return HarvestMapper.INSTANCE.toDTO(savedHarvest);
    }

    @Transactional
    @Override
    public HarvestDTO updateHarvest(Long id, HarvestDTO harvestDTO) {
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

        return HarvestMapper.INSTANCE.toDTO(updatedHarvest);
    }

    @Transactional
    @Override
    public HarvestDTO deleteHarvest(Long id) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Harvest not found with ID: " + id));

        harvestRepository.delete(harvest);

        return HarvestMapper.INSTANCE.toDTO(harvest);
    }

    public List<HarvestDTO> getHarvests() {
        return harvestRepository.findAll().stream()
                .map(HarvestMapper.INSTANCE::toDTO)
                .toList();
    }

}
