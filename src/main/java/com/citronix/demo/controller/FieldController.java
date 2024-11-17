package com.citronix.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.citronix.demo.mapper.FieldMapper;
import com.citronix.demo.model.Field;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/fields")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @PostMapping
    public FieldDTO createField(@RequestBody @Valid FieldDTO fieldDTO) {
        return fieldService.createField(fieldDTO);
    }

    @GetMapping
    public List<FieldDTO> getAllFields() {
        return fieldService.getAllFields();
    }

    @GetMapping("/{id}")
    public FieldDTO getFieldById(@PathVariable Long id) {
        return fieldService.getFieldById(id);
    }

    @PutMapping("/{id}")
    public FieldDTO updateField(@PathVariable Long id, @RequestBody @Valid FieldDTO fieldDTO) {
        return fieldService.updateField(id, fieldDTO);
    }

     @DeleteMapping("/{id}")
    public void deleteField(@PathVariable Long id) {
        fieldService.deleteField(id);
    }
}