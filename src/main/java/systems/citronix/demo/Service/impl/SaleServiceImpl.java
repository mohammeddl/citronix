package systems.citronix.demo.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import systems.citronix.demo.Service.SaleService;
import systems.citronix.demo.dto.request.SaleRequestDTO;
import systems.citronix.demo.dto.response.SaleResponseDTO;
import systems.citronix.demo.exception.CustomNotFoundException;
import systems.citronix.demo.exception.ValidationException;
import systems.citronix.demo.mapper.SaleMapper;
import systems.citronix.demo.model.Harvest;
import systems.citronix.demo.model.Sale;
import systems.citronix.demo.repository.HarvestRepository;
import systems.citronix.demo.repository.SaleRepository;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private HarvestRepository harvestRepository;

    @Autowired
    private SaleMapper SaleMapper;

    public SaleResponseDTO createSale(SaleRequestDTO saleDTO) {
        Harvest harvest = harvestRepository.findById(saleDTO.harvestId())
                .orElseThrow(() -> new CustomNotFoundException("Harvest not found with ID: " + saleDTO.harvestId()));
        if (harvest.getSale() != null) {
            throw new ValidationException("This harvest is already sold to a client.");
        }
        Sale sale = SaleMapper.toEntity(saleDTO);
        sale.setHarvest(harvest);
        sale.setQuantity(harvest.getQuantity());
        sale.setRevenue(sale.getQuantity() * sale.getUnitPrice());

        Sale savedSale = saleRepository.save(sale);
        return SaleMapper.toResponseDTO(savedSale);
    }

    @Override
    public List<SaleResponseDTO> getAllSales() {
        return saleRepository.findAll().stream()
                .map(SaleMapper::toResponseDTO)
                .toList();
    }

    @Override
    public SaleResponseDTO updateSale(Long id, SaleRequestDTO saleDTO) {
        Sale existingSale = saleRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Sale not found with ID: " + id));

        Harvest harvest = harvestRepository.findById(saleDTO.harvestId())
                .orElseThrow(() -> new CustomNotFoundException("Harvest not found with ID: " + saleDTO.harvestId()));

        if (!existingSale.getHarvest().getId().equals(harvest.getId())) {
            throw new ValidationException("You cannot change the harvest for an existing sale.");
        }

        existingSale.setUnitPrice(saleDTO.unitPrice());
        existingSale.setDate(saleDTO.date());
        existingSale.setClient(saleDTO.client());

        existingSale.setRevenue(existingSale.getQuantity() * existingSale.getUnitPrice());
        Sale updatedSale = saleRepository.save(existingSale);

        return SaleMapper.toResponseDTO(updatedSale);
    }

    public void deleteSale(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Sale not found with ID: " + id));
        saleRepository.delete(sale);
    }

}
