package com.citronix.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.citronix.demo.Service.FarmService;
import com.citronix.demo.dto.FarmDTO;
import com.citronix.demo.dto.FarmSearchCriteria;
import com.citronix.demo.mapper.FarmMapper;
import com.citronix.demo.model.Farm;

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
    public FarmDTO createFarm(@RequestBody @Valid FarmDTO farmDTO) {
        Farm farm = farmService.createFarm(farmDTO);
        return FarmMapper.INSTANCE.toDTO(farm);
    }

    @GetMapping
    public List<FarmDTO> getAllFarms() {
        return farmService.getAllFarms().stream()
                .map(FarmMapper.INSTANCE::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public FarmDTO getFarmById(@PathVariable Long id) {
        return FarmMapper.INSTANCE.toDTO(farmService.getFarmById(id));
    }

    @PutMapping("/{id}")
    public FarmDTO updateFarm(@PathVariable Long id, @RequestBody @Valid FarmDTO farmDTO) {
        Farm farm = farmService.updateFarm(id, farmDTO);
        return FarmMapper.INSTANCE.toDTO(farm);
    }

    @DeleteMapping("/{id}")
    public void deleteFarm(@PathVariable Long id) {
        farmService.deleteFarm(id);
    }

    @PostMapping("/search")
    public ResponseEntity<List<FarmDTO>> searchFarms(@RequestBody FarmSearchCriteria criteria) {
        List<FarmDTO> farms = farmService.searchFarms(criteria);
        return ResponseEntity.ok(farms);
    }
}
