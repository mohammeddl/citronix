package systems.citronix.demo.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import systems.citronix.demo.Service.TreeService;
import systems.citronix.demo.dto.request.TreeRequestDTO;
import systems.citronix.demo.dto.response.TreeResponseDTO;
import systems.citronix.demo.exception.CustomNotFoundException;
import systems.citronix.demo.exception.ValidationException;
import systems.citronix.demo.mapper.TreeMapper;
import systems.citronix.demo.model.Field;
import systems.citronix.demo.model.Tree;
import systems.citronix.demo.repository.FieldRepository;
import systems.citronix.demo.repository.TreeRepository;

@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    private TreeRepository treeRepository;
    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private TreeMapper treeMapper;

    @Transactional
    public TreeResponseDTO  createTree(TreeRequestDTO treeDTO) {
        Field field = fieldRepository.findById(treeDTO.fieldId())
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + treeDTO.fieldId()));

        validateTreeSpacing(field);
        Tree tree = treeMapper.toEntity(treeDTO);
        tree.setField(field);

        Tree savedTree = treeRepository.save(tree);
        return treeMapper.toResponseDTO(savedTree);
    }

    private void validateTreeSpacing(Field field) {
        int maxTrees = (int) (field.getSurface() * 100);

        long currentTreeCount = treeRepository.countByFieldId(field.getId());

        if (currentTreeCount >= maxTrees) {
            throw new ValidationException("The field exceeds the maximum tree density of 100 trees per hectare.");
        }
    }

    public double calculateProductivity(Long id) {
        Tree tree = treeRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Tree not found with ID: " + id));
        return tree.calculateProductivity();
    }

    @Transactional
    @Override
    public TreeResponseDTO  updateTree(Long id, TreeRequestDTO treeDTO) {
        Tree existingTree = treeRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Tree not found with ID: " + id));

        Field field = fieldRepository.findById(treeDTO.fieldId())
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + treeDTO.fieldId()));

        validateTreeSpacing(field);

        existingTree.setPlantingDate(treeDTO.plantingDate());
        existingTree.setField(field);

        Tree updatedTree = treeRepository.save(existingTree);

        return treeMapper.toResponseDTO(updatedTree);
    }

    public List<TreeResponseDTO > getAllTrees() {
        return treeRepository.findAll().stream()
                .map(treeMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    @Override
    public TreeResponseDTO  deleteTree(Long id) {
        Tree tree = treeRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Tree not found with ID: " + id));
        treeRepository.delete(tree);
        return treeMapper.toResponseDTO(tree);

    }

}
