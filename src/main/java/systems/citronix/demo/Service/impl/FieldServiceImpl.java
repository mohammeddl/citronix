package systems.citronix.demo.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import systems.citronix.demo.Service.FieldService;
import systems.citronix.demo.dto.request.FieldRequestDTO;
import systems.citronix.demo.dto.response.FieldResponseDTO;
import systems.citronix.demo.exception.CustomNotFoundException;
import systems.citronix.demo.exception.ValidationException;
import systems.citronix.demo.mapper.FieldMapper;
import systems.citronix.demo.model.Farm;
import systems.citronix.demo.model.Field;
import systems.citronix.demo.repository.FarmRepository;
import systems.citronix.demo.repository.FieldRepository;

@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private FieldMapper fieldMapper;

    @Transactional
    public FieldResponseDTO createField(FieldRequestDTO fieldDTO) {
        Farm farm = farmRepository.findById(fieldDTO.farmId())
                .orElseThrow(() -> new CustomNotFoundException("Farm not found with ID: " + fieldDTO.farmId()));

        validateFieldConstraints(farm, fieldDTO);
        Field field = fieldMapper.toEntity(fieldDTO);
        field.setFarm(farm);
        Field savedField = fieldRepository.save(field);
        return fieldMapper.toResponseDTO(savedField);
    }

    @Override
    public List<FieldResponseDTO> getAllFields() {
        List<Field> fields = fieldRepository.findAll();
        return fieldMapper.toResponseDTOList(fields);
    }

    public FieldResponseDTO getFieldById(Long id) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + id));
        return fieldMapper.toResponseDTO(field);
    }

    @Transactional
    public FieldResponseDTO updateField(Long id, FieldRequestDTO fieldDTO) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + id));

        Farm farm = farmRepository.findById(fieldDTO.farmId())
                .orElseThrow(() -> new CustomNotFoundException("Farm not found with ID: " + fieldDTO.farmId()));

        validateFieldConstraints(farm, fieldDTO);
        field.setName(fieldDTO.name());
        field.setSurface(fieldDTO.surface());
        field.setFarm(farm);

        Field updatedField = fieldRepository.save(field);
        return fieldMapper.toResponseDTO(updatedField);
    }

    @Transactional
    @Override
    public FieldResponseDTO deleteField(Long id) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Field not found with ID: " + id));
        fieldRepository.delete(field);
        return fieldMapper.toResponseDTO(field);
    }

    private void validateFieldConstraints(Farm farm, FieldRequestDTO fieldDTO) {
        if (fieldDTO.surface() < 0.1) {
            throw new ValidationException("The field surface must be at least 0.1 hectares.");
        }

        double maxFieldSurface = farm.getSurface() * 0.5;
        if (fieldDTO.surface() > maxFieldSurface) {
            throw new ValidationException("The field surface cannot exceed 50% of the farm's total surface.");
        }

        long fieldCount = fieldRepository.countByFarmId(farm.getId());
        if (fieldCount >= 10) {
            throw new ValidationException("A farm cannot contain more than 10 fields.");
        }
    }

}
