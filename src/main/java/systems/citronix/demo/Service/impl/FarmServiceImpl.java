package systems.citronix.demo.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import systems.citronix.demo.Service.FarmService;
import systems.citronix.demo.dto.request.FarmRequestDTO;
import systems.citronix.demo.dto.request.FarmSearchCriteria;
import systems.citronix.demo.dto.response.FarmResponseDTO;
import systems.citronix.demo.exception.CustomNotFoundException;
import systems.citronix.demo.mapper.FarmMapper;
import systems.citronix.demo.model.Farm;
import systems.citronix.demo.repository.FarmRepository;

@Service
public class FarmServiceImpl implements FarmService {

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private FarmMapper farmMapper;

    @Transactional
    public FarmResponseDTO  createFarm(FarmRequestDTO farmRequestDTO) {
        Farm farm = farmMapper.toEntity(farmRequestDTO);
        Farm savedFarm = farmRepository.save(farm);
        return farmMapper.toResponseDTO(savedFarm);
    }

    public Page<FarmResponseDTO> getAllFarms(Pageable pageable) {
        Page<Farm> farms = farmRepository.findAll(pageable);
        return farms
                .map(farmMapper::toResponseDTO);
    }

    public Page<Farm> getAllFarms2(Pageable pageable) {
        Page<Farm> farms = farmRepository.findAll(pageable);
        return farms;
    }

    public FarmResponseDTO getFarmById(Long id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Farm not found with ID: " + id));
        return farmMapper.toResponseDTO(farm);
    }

    @Transactional
    public FarmResponseDTO updateFarm(Long id, FarmRequestDTO farmDTO) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Farm not found with ID: " + id));

        farm.setName(farmDTO.name());
        farm.setLocalization(farmDTO.localization());
        farm.setSurface(farmDTO.surface());
        farm.setCreationDate(farmDTO.creationDate());

        Farm updatedFarm = farmRepository.save(farm);
        return farmMapper.toResponseDTO(updatedFarm);
    }

    @Transactional
    public void deleteFarm(Long id) {
        if (!farmRepository.existsById(id)) {
            throw new CustomNotFoundException("Farm not found with ID: " + id);
        }
        farmRepository.deleteById(id);
    }

    public List<FarmResponseDTO> searchFarms(FarmSearchCriteria criteria) {
        return farmRepository.searchFarm(criteria).stream()
                .map(farmMapper::toResponseDTO)
                .toList();
    }

    
}
