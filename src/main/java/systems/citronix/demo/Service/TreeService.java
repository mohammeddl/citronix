package systems.citronix.demo.Service;

import java.util.List;

import systems.citronix.demo.dto.request.TreeRequestDTO;
import systems.citronix.demo.dto.response.TreeResponseDTO;

public interface TreeService {

    TreeResponseDTO  createTree(TreeRequestDTO treeDTO);
    double calculateProductivity(Long treeId);
    List<TreeResponseDTO > getAllTrees();
    TreeResponseDTO  deleteTree(Long treeId);
    TreeResponseDTO  updateTree(Long treeId, TreeRequestDTO treeDTO);
    
} 
