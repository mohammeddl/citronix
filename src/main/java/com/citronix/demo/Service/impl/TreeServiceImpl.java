package com.citronix.demo.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citronix.demo.Service.TreeService;
import com.citronix.demo.dto.TreeDTO;
import com.citronix.demo.exception.CustomNotFoundException;
import com.citronix.demo.mapper.TreeMapper;
import com.citronix.demo.model.Field;
import com.citronix.demo.model.Tree;
import com.citronix.demo.repository.FieldRepository;
import com.citronix.demo.repository.TreeRepository;

@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    private TreeRepository treeRepository;
    @Autowired
    private FieldRepository fieldRepository;

    public TreeDTO createTree(TreeDTO treeDTO) {
        Field field = fieldRepository.findById(treeDTO.fieldId())
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + treeDTO.fieldId()));

        Tree tree = TreeMapper.INSTANCE.toEntity(treeDTO);
        tree.setField(field);

        Tree savedTree = treeRepository.save(tree);
        return TreeMapper.INSTANCE.toDTO(savedTree);
    }

    public double calculateProductivity(Long id) {
        Tree tree = treeRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Tree not found with ID: " + id));
        return tree.calculateProductivity();
    }

    public List<TreeDTO> getAllTrees() {
        return treeRepository.findAll().stream()
                .map(TreeMapper.INSTANCE::toDTO)
                .toList();
    }
}
