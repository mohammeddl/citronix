package com.citronix.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/harvests")
public class HarvestController {

    @Autowired
    private HarvestService harvestService;

    @PostMapping
    public ResponseEntity<HarvestDTO> createHarvest(@RequestBody @Valid HarvestDTO harvestDTO) {
        HarvestDTO createdHarvest = harvestService.createHarvest(harvestDTO);
        return ResponseEntity.ok(createdHarvest);
    }

    @GetMapping
    public ResponseEntity<List<HarvestDTO>> getAllHarvests() {
        List<HarvestDTO> getAllHarvests = harvestService.getHarvests();
        return ResponseEntity.ok(getAllHarvests);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HarvestDTO> updateHarvest(@PathVariable Long id, @RequestBody @Valid HarvestDTO harvestDTO) {
        HarvestDTO updatedHarvest = harvestService.updateHarvest(id, harvestDTO);
        return ResponseEntity.ok(updatedHarvest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHarvest(@PathVariable Long id) {
        harvestService.deleteHarvest(id);
        return ResponseEntity.noContent().build();
    }

}
