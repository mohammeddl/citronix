package systems.citronix.demo.Service;

import java.util.List;

import systems.citronix.demo.dto.request.FieldRequestDTO;
import systems.citronix.demo.dto.response.FieldResponseDTO;

public interface FieldService {
    FieldResponseDTO createField(FieldRequestDTO fieldDTO);
    FieldResponseDTO getFieldById(Long id);
    FieldResponseDTO updateField(Long id, FieldRequestDTO fieldDTO);
    FieldResponseDTO deleteField(Long id);
    List<FieldResponseDTO> getAllFields();


    
} 
