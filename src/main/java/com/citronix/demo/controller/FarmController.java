package com.citronix.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.citronix.demo.Service.FarmService;
import com.citronix.demo.dto.FarmDTO;
import com.citronix.demo.dto.FarmSearchCriteria;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
@RequiredArgsConstructor
public class FarmController {

    @Autowired
    private  FarmService farmService;


    @PostMapping
    public ResponseEntity<FarmDTO> createFarm(@RequestBody @Valid FarmDTO farmDTO) {
        return ResponseEntity.ok(farmService.createFarm(farmDTO));
    }
    @GetMapping
    public ResponseEntity<List<FarmDTO>> getAllFarms() {
        return ResponseEntity.ok(farmService.getAllFarms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmDTO> getFarmById(@PathVariable Long id) {
        return ResponseEntity.ok(farmService.getFarmById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmDTO> updateFarm(@PathVariable Long id, @RequestBody @Valid FarmDTO farmDTO) {
        return ResponseEntity.ok(farmService.updateFarm(id, farmDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id) {
        farmService.deleteFarm(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<List<FarmDTO>> searchFarms(@RequestBody FarmSearchCriteria criteria) {
        return ResponseEntity.ok(farmService.searchFarms(criteria));
    }

}
