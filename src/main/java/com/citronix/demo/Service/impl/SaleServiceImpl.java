package com.citronix.demo.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citronix.demo.Service.SaleService;
import com.citronix.demo.dto.SaleDTO;
import com.citronix.demo.exception.CustomNotFoundException;
import com.citronix.demo.exception.ValidationException;
import com.citronix.demo.mapper.SaleMapper;
import com.citronix.demo.model.Harvest;
import com.citronix.demo.model.Sale;
import com.citronix.demo.repository.HarvestRepository;
import com.citronix.demo.repository.SaleRepository;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private HarvestRepository harvestRepository;

    public SaleDTO createSale(SaleDTO saleDTO) {
        Harvest harvest = harvestRepository.findById(saleDTO.harvestId())
                .orElseThrow(() -> new CustomNotFoundException("Harvest not found with ID: " + saleDTO.harvestId()));
        if (harvest.getSale() != null) {
            throw new ValidationException("This harvest is already sold to a client.");
        }
        Sale sale = SaleMapper.INSTANCE.toEntity(saleDTO);
        sale.setHarvest(harvest);
        sale.setQuantity(harvest.getQuantity());
        sale.setRevenue(sale.getQuantity() * sale.getUnitPrice());

        Sale savedSale = saleRepository.save(sale);
        return SaleMapper.INSTANCE.toDTO(savedSale);
    }

    @Override
    public List<SaleDTO> getAllSales() {
        return saleRepository.findAll().stream()
                .map(SaleMapper.INSTANCE::toDTO)
                .toList();
    }

}
