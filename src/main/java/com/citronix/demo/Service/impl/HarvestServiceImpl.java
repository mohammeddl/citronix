package com.citronix.demo.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citronix.demo.Service.HarvestService;
import com.citronix.demo.dto.HarvestDTO;
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

        if (harvest.getTreeHarvestDetails() != null) {
            for (TreeHarvestDetail detail : harvest.getTreeHarvestDetails()) {
                // Fetch the actual Tree entity and validate
                Tree tree = treeRepository.findById(detail.getTree().getId())
                        .orElseThrow(() -> new CustomNotFoundException(
                                "Tree not found with ID: " + detail.getTree().getId()));
                detail.setTree(tree);
                detail.setHarvest(harvest); // Link to the parent Harvest
            }
        }

        Harvest savedHarvest = harvestRepository.save(harvest);

        return HarvestMapper.INSTANCE.toDTO(savedHarvest);
    }

    public List<HarvestDTO> getHarvests() {
        return harvestRepository.findAll().stream()
                .map(HarvestMapper.INSTANCE::toDTO)
                .toList();
    }

}
