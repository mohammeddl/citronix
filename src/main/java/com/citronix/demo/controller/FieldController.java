package com.citronix.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
}