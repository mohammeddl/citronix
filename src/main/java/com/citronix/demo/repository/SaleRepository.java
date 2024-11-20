package com.citronix.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citronix.demo.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    
}
