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

import com.citronix.demo.Service.FieldService;
import com.citronix.demo.dto.FieldDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/fields")
@Tag(name = "Fields", description = "Endpoints for managing fields")
@RequiredArgsConstructor
public class FieldController {

    
    private final FieldService fieldService;

    @PostMapping
    @Operation(summary = "Create a new field", description = "Create a new field with the specified details")
    public ResponseEntity<FieldDTO> createField(@RequestBody @Valid FieldDTO fieldDTO) {
        FieldDTO createdField = fieldService.createField(fieldDTO);
        return ResponseEntity.ok(createdField);
    }

    @GetMapping
    public ResponseEntity<List<FieldDTO>> getAllFields() {
        return ResponseEntity.ok(fieldService.getAllFields());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FieldDTO> getFieldById(@PathVariable Long id) {
        FieldDTO field = fieldService.getFieldById(id);
        return ResponseEntity.ok(field);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FieldDTO> updateField(@PathVariable Long id, @RequestBody @Valid FieldDTO fieldDTO) {
        FieldDTO updatedField = fieldService.updateField(id, fieldDTO);
        return ResponseEntity.ok(updatedField);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FieldDTO> deleteField(@PathVariable Long id) {
        FieldDTO deletedField = fieldService.deleteField(id);
        return ResponseEntity.ok(deletedField); 
    }
}