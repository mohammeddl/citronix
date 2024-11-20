package com.citronix.demo.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citronix.demo.Service.HarvestService;
import com.citronix.demo.dto.HarvestDTO;
import com.citronix.demo.dto.TreeHarvestDetailDTO;
import com.citronix.demo.exception.CustomNotFoundException;
import com.citronix.demo.mapper.HarvestMapper;
import com.citronix.demo.model.Field;
import com.citronix.demo.model.Harvest;
import com.citronix.demo.model.Tree;
import com.citronix.demo.model.TreeHarvestDetail;
import com.citronix.demo.repository.FieldRepository;
import com.citronix.demo.repository.HarvestRepository;
import com.citronix.demo.repository.TreeRepository;

@Service
public class HarvestServiceImpl implements HarvestService {

    @Autowired
    private HarvestRepository harvestRepository;

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private TreeRepository treeRepository;

    public HarvestDTO createHarvest(HarvestDTO harvestDTO) {
        Field field = fieldRepository.findById(harvestDTO.fieldId())
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + harvestDTO.fieldId()));

        if (harvestRepository.existsBySeasonAndFieldId(harvestDTO.season(), harvestDTO.fieldId())) {
            throw new IllegalArgumentException("A harvest already exists for this season and field.");
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

    public List<HarvestDTO> getHarvests() {
        return harvestRepository.findAll().stream()
                .map(HarvestMapper.INSTANCE::toDTO)
                .toList();
    }

    public void deleteHarvest(Long id) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Harvest not found with ID: " + id));
        harvestRepository.delete(harvest);
    }

    public HarvestDTO updateHarvest(Long id, HarvestDTO harvestDTO) {
        Harvest existingHarvest = harvestRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Harvest not found with ID: " + id));
    
        // Fetch and validate the associated field
        Field field = fieldRepository.findById(harvestDTO.fieldId())
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + harvestDTO.fieldId()));
    
        if (!existingHarvest.getField().getId().equals(field.getId())) {
            throw new IllegalArgumentException("You cannot change the field for an existing harvest.");
        }
    
        // Update basic harvest details
        existingHarvest.setSeason(harvestDTO.season());
        existingHarvest.setDate(harvestDTO.date());
    
        double totalQuantity = 0.0;
    
        // Clear existing TreeHarvestDetails and re-add them
        existingHarvest.getTreeHarvestDetails().clear();
        if (harvestDTO.treeHarvestDetails() != null) {
            for (TreeHarvestDetailDTO detailDTO : harvestDTO.treeHarvestDetails()) {
                // Fetch the tree
                Tree tree = treeRepository.findById(detailDTO.treeId())
                        .orElseThrow(() -> new CustomNotFoundException(
                                "Tree not found with ID: " + detailDTO.treeId()));
    
                // Calculate productivity
                double treeProductivity = tree.calculateProductivity();
    
                // Create new TreeHarvestDetail
                TreeHarvestDetail detail = new TreeHarvestDetail();
                detail.setTree(tree);
                detail.setHarvest(existingHarvest);
                detail.setQuantity(treeProductivity);
    
                existingHarvest.getTreeHarvestDetails().add(detail);
                totalQuantity += treeProductivity;
            }
        }
        existingHarvest.setQuantity(totalQuantity);
        Harvest updatedHarvest = harvestRepository.save(existingHarvest);
    
        return HarvestMapper.INSTANCE.toDTO(updatedHarvest);
    }
    
}
