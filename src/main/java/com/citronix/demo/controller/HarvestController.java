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
        return ResponseEntity.ok(harvestService.createHarvest(harvestDTO));
    }

    @GetMapping
    public ResponseEntity<List<HarvestDTO>> getAllHarvests() {
        return ResponseEntity.ok(harvestService.getHarvests());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HarvestDTO> updateHarvest(@PathVariable Long id, @RequestBody @Valid HarvestDTO harvestDTO) {
        return ResponseEntity.ok(harvestService.updateHarvest(id, harvestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HarvestDTO> deleteHarvest(@PathVariable Long id) {
        return ResponseEntity.ok(harvestService.deleteHarvest(id));
    }

}
