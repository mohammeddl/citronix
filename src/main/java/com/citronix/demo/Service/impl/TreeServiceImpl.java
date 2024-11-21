package com.citronix.demo.Service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citronix.demo.Service.TreeService;
import com.citronix.demo.dto.HarvestDTO;
import com.citronix.demo.dto.TreeDTO;
import com.citronix.demo.exception.CustomNotFoundException;
import com.citronix.demo.exception.ValidationException;
import com.citronix.demo.mapper.HarvestMapper;
import com.citronix.demo.mapper.TreeMapper;
import com.citronix.demo.model.Field;
import com.citronix.demo.model.Harvest;
import com.citronix.demo.model.Tree;
import com.citronix.demo.repository.FieldRepository;
import com.citronix.demo.repository.TreeRepository;

import jakarta.transaction.Transactional;

@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    private TreeRepository treeRepository;
    @Autowired
    private FieldRepository fieldRepository;

    @Transactional
    public TreeDTO createTree(TreeDTO treeDTO) {
        Field field = fieldRepository.findById(treeDTO.fieldId())
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + treeDTO.fieldId()));

        validateTreeSpacing(field);
        Tree tree = TreeMapper.INSTANCE.toEntity(treeDTO);
        tree.setField(field);
        tree.setAge(calculateAge(tree.getPlantingDate()));

        Tree savedTree = treeRepository.save(tree);
        return TreeMapper.INSTANCE.toDTO(savedTree);
    }

    private void validateTreeSpacing(Field field) {
        int maxTrees = (int) (field.getSurface() * 100);

        long currentTreeCount = treeRepository.countByFieldId(field.getId());

        if (currentTreeCount >= maxTrees) {
            throw new ValidationException("The field exceeds the maximum tree density of 100 trees per hectare.");
        }
    }

    private int calculateAge(LocalDate plantingDate) {
        return Period.between(plantingDate, LocalDate.now()).getYears();
    }

    public double calculateProductivity(Long id) {
        Tree tree = treeRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Tree not found with ID: " + id));
        return tree.calculateProductivity();
    }

    @Transactional
    @Override
    public TreeDTO updateTree(Long id, TreeDTO treeDTO) {
        Tree existingTree = treeRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Tree not found with ID: " + id));

        Field field = fieldRepository.findById(treeDTO.fieldId())
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + treeDTO.fieldId()));

        validateTreeSpacing(field);

        existingTree.setPlantingDate(treeDTO.plantingDate());
        existingTree.setField(field);
        existingTree.setAge(calculateAge(existingTree.getPlantingDate()));

        Tree updatedTree = treeRepository.save(existingTree);

        return TreeMapper.INSTANCE.toDTO(updatedTree);

    }

    public List<TreeDTO> getAllTrees() {
        return treeRepository.findAll().stream()
                .map(TreeMapper.INSTANCE::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public TreeDTO deleteTree(Long id) {
        Tree tree = treeRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Tree not found with ID: " + id));
        treeRepository.delete(tree);
        return TreeMapper.INSTANCE.toDTO(tree);

    }

}
