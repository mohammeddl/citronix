package com.citronix.demo.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.citronix.demo.Service.SaleService;
import com.citronix.demo.repository.SaleRepository;

public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    
    
}
