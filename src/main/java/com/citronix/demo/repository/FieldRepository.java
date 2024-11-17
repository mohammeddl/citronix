package com.citronix.demo.repository;

import com.citronix.demo.model.Field;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field, Long> {
    long countByFarmId(Long farmId);
    List<Field> findAllByFarmId(Long farmId);

}
