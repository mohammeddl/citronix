package com.citronix.demo.Service;

import java.util.List;

import com.citronix.demo.dto.TreeDTO;

public interface TreeService {

    TreeDTO createTree(TreeDTO treeDTO);
    double calculateProductivity(Long treeId);
    List<TreeDTO> getAllTrees();
    
} 
