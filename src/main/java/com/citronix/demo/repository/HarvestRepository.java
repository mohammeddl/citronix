package com.citronix.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.citronix.demo.model.Harvest;
import com.citronix.demo.model.Season;

public interface HarvestRepository extends JpaRepository<Harvest, Long> {
    boolean existsBySeasonAndFieldId(Season season, Long fieldId);
}
