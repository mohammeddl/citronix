package com.citronix.demo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.citronix.demo.Service.FarmService;
import com.citronix.demo.dto.FarmDTO;
import com.citronix.demo.dto.FarmSearchCriteria;
import com.citronix.demo.model.Farm;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
@RequiredArgsConstructor
@Tag(name = "Farms", description = "Endpoints for managing farms")
public class FarmController {

    private final FarmService farmService;

    @PostMapping
    @Operation(summary = "Create a new farm", description = "Create a new farm with the specified details")
    public ResponseEntity<FarmDTO> createFarm(@RequestBody @Valid FarmDTO farmDTO) {
        return ResponseEntity.ok(farmService.createFarm(farmDTO));
    }

    @GetMapping
    @Operation(summary = "Get all farms", description = "Retrieve a list of all farms")
    public ResponseEntity<Page<FarmDTO>> getAllFarms(Pageable pageable) {
        return ResponseEntity.ok(farmService.getAllFarms(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get farm by id", description = "Retrieve a farm by its id")
    public ResponseEntity<FarmDTO> getFarmById(@PathVariable Long id) {
        return ResponseEntity.ok(farmService.getFarmById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update farm", description = "Update the farm with the specified id")
    public ResponseEntity<FarmDTO> updateFarm(@PathVariable Long id, @RequestBody @Valid FarmDTO farmDTO) {
        return ResponseEntity.ok(farmService.updateFarm(id, farmDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete farm", description = "Delete the farm with the specified id")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id) {
        farmService.deleteFarm(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    @Operation(summary = "Search farms", description = "Search farms by criteria")
    public ResponseEntity<List<FarmDTO>> searchFarms(@RequestBody FarmSearchCriteria criteria) {
        return ResponseEntity.ok(farmService.searchFarms(criteria));
    }

   

}
