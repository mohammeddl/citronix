package com.citronix.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public HarvestDTO createHarvest(@RequestBody @Valid HarvestDTO harvestDTO) {
        return harvestService.createHarvest(harvestDTO);
    }

    @GetMapping
    public List<HarvestDTO> getAllHarvests() {
        return harvestService.getHarvests();
    }

}
