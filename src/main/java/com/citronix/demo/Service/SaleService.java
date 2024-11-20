package com.citronix.demo.Service;

import java.util.List;

import com.citronix.demo.dto.SaleDTO;

public interface SaleService {
    SaleDTO createSale(SaleDTO saleDTO);
    List<SaleDTO> getAllSales();
    SaleDTO updateSale(Long id, SaleDTO saleDTO);
    void deleteSale(Long id);


    
} 
