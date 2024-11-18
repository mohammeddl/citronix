package com.citronix.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public TreeDTO createTree(@RequestBody @Valid TreeDTO treeDTO) {
        return treeService.createTree(treeDTO);
    }

    @GetMapping("/{id}/productivity")
    public double calculateProductivity(@PathVariable Long id) {
        return treeService.calculateProductivity(id);
    }

    @GetMapping
    public List<TreeDTO> getAllTrees() {
        return treeService.getAllTrees();
    }
}
