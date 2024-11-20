package com.citronix.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citronix.demo.Service.TreeService;
import com.citronix.demo.dto.TreeDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/trees")
public class TreeController {

    @Autowired
    private TreeService treeService;

    @PostMapping
    public ResponseEntity<TreeDTO> createTree(@RequestBody @Valid TreeDTO treeDTO) {
        TreeDTO createdTree = treeService.createTree(treeDTO);
        return new ResponseEntity<>(createdTree, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/productivity")
    public ResponseEntity<Double> calculateProductivity(@PathVariable Long id) {
        double productivity = treeService.calculateProductivity(id);
        return ResponseEntity.ok(productivity);
    }

    @GetMapping
    public ResponseEntity<List<TreeDTO>> getAllTrees() {
        List<TreeDTO> trees = treeService.getAllTrees();
        return ResponseEntity.ok(trees);
    }
}
