package com.citronix.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citronix.demo.Service.TreeService;
import com.citronix.demo.dto.TreeDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/trees")
@Tag(name = "Trees", description = "Endpoints for managing trees")
@RequiredArgsConstructor
public class TreeController {

    private final TreeService treeService;

    @PostMapping
    @Operation(summary = "Create a new tree", description = "Create a new tree with the specified details")
    public ResponseEntity<TreeDTO> createTree(@RequestBody @Valid TreeDTO treeDTO) {
        TreeDTO createdTree = treeService.createTree(treeDTO);
        return new ResponseEntity<>(createdTree, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/productivity")
    @Operation(summary = "Calculate productivity", description = "Calculate the productivity of the tree with the specified id")
    public ResponseEntity<Double> calculateProductivity(@PathVariable Long id) {
        double productivity = treeService.calculateProductivity(id);
        return ResponseEntity.ok(productivity);
    }

    @GetMapping
    @Operation(summary = "Get all trees", description = "Retrieve a list of all trees")
    public ResponseEntity<List<TreeDTO>> getAllTrees() {
        List<TreeDTO> trees = treeService.getAllTrees();
        return ResponseEntity.ok(trees);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete tree", description = "Delete the tree with the specified id")
    public ResponseEntity<TreeDTO> deleteTree(@PathVariable Long id) {
        return ResponseEntity.ok(treeService.deleteTree(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update tree", description = "Update the tree with the specified id")
    public ResponseEntity<TreeDTO> updateTree(@PathVariable Long id, @RequestBody @Valid TreeDTO treeDTO) {
        TreeDTO updatedTree = treeService.updateTree(id, treeDTO);
        return ResponseEntity.ok(updatedTree);
    }

}
