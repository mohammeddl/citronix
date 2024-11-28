package systems.citronix.demo.controller;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import systems.citronix.demo.Service.FieldService;
import systems.citronix.demo.dto.request.FieldRequestDTO;
import systems.citronix.demo.dto.response.FieldResponseDTO;

@RestController
@RequestMapping("/api/fields")
@Tag(name = "Fields", description = "Endpoints for managing fields")
@RequiredArgsConstructor
public class FieldController {

    
    private final FieldService fieldService;

    @PostMapping
    @Operation(summary = "Create a new field", description = "Create a new field with the specified details")
    public ResponseEntity<FieldResponseDTO> createField(@RequestBody @Valid FieldRequestDTO fieldDTO) {
        FieldResponseDTO createdField = fieldService.createField(fieldDTO);
        return ResponseEntity.ok(createdField);
    }

    @GetMapping
    public ResponseEntity<List<FieldResponseDTO>> getAllFields() {
        return ResponseEntity.ok(fieldService.getAllFields());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FieldResponseDTO> getFieldById(@PathVariable Long id) {
        FieldResponseDTO field = fieldService.getFieldById(id);
        return ResponseEntity.ok(field);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FieldResponseDTO> updateField(@PathVariable Long id, @RequestBody @Valid FieldRequestDTO fieldDTO) {
        FieldResponseDTO updatedField = fieldService.updateField(id, fieldDTO);
        return ResponseEntity.ok(updatedField);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FieldResponseDTO> deleteField(@PathVariable Long id) {
        FieldResponseDTO deletedField = fieldService.deleteField(id);
        return ResponseEntity.ok(deletedField); 
    }
}