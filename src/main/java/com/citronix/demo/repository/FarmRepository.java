package com.citronix.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citronix.demo.model.Farm;

public interface FarmRepository extends JpaRepository<Farm, Long>,FarmRepositoryCustom  {
    
}