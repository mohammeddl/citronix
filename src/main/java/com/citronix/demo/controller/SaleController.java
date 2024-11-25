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

import com.citronix.demo.Service.SaleService;
import com.citronix.demo.dto.SaleDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sales")
@Tag(name = "Sales", description = "Endpoints for managing sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    @Operation(summary = "Create a new sale", description = "Create a new sale with the specified details")
    public ResponseEntity<SaleDTO> createSale(@RequestBody @Valid SaleDTO saleDTO) {
        SaleDTO createdSale = saleService.createSale(saleDTO);
        return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all sales", description = "Retrieve a list of all sales")
    public ResponseEntity<List<SaleDTO>> getAllSales() {
        return ResponseEntity.ok(saleService.getAllSales());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update sale", description = "Update the sale with the specified id")
    public ResponseEntity<SaleDTO> updateSale(@PathVariable Long id, @RequestBody @Valid SaleDTO saleDTO) {
        SaleDTO updatedSale = saleService.updateSale(id, saleDTO);
        return ResponseEntity.ok(updatedSale);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete sale", description = "Delete the sale with the specified id")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }

}
