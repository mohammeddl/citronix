package com.citronix.demo.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citronix.demo.Service.HarvestService;
import com.citronix.demo.dto.HarvestDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/harvests")
@RequiredArgsConstructor
@Tag(name = "Harvests", description = "Endpoints for managing harvests")
public class HarvestController {

    private final HarvestService harvestService;

    @PostMapping
    @Operation(summary = "Create a new harvest", description = "Create a new harvest with the specified details")
    public ResponseEntity<HarvestDTO> createHarvest(@RequestBody @Valid HarvestDTO harvestDTO) {
        return ResponseEntity.ok(harvestService.createHarvest(harvestDTO));
    }

    @GetMapping
    @Operation(summary = "Get all harvests", description = "Retrieve a list of all harvests")
    public ResponseEntity<List<HarvestDTO>> getAllHarvests() {
        return ResponseEntity.ok(harvestService.getHarvests());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update harvest", description = "Update the harvest with the specified id")
    public ResponseEntity<HarvestDTO> updateHarvest(@PathVariable Long id, @RequestBody @Valid HarvestDTO harvestDTO) {
        return ResponseEntity.ok(harvestService.updateHarvest(id, harvestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete harvest", description = "Delete the harvest with the specified id")
    public ResponseEntity<HarvestDTO> deleteHarvest(@PathVariable Long id) {
        return ResponseEntity.ok(harvestService.deleteHarvest(id));
    }

}
