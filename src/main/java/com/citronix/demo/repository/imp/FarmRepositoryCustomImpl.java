package com.citronix.demo.repository.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.citronix.demo.dto.FarmSearchCriteria;
import com.citronix.demo.model.Farm;
import com.citronix.demo.repository.FarmRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

@Repository
public class FarmRepositoryCustomImpl implements FarmRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Farm> searchFarm(FarmSearchCriteria farmSearchCriteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Farm> query = cb.createQuery(Farm.class);
        Root<Farm> farm = query.from(Farm.class);

        List<Predicate> predicates = new ArrayList<>();

        if (farmSearchCriteria.name() != null && !farmSearchCriteria.name().isBlank()) {
            predicates.add(cb.like(cb.lower(farm.get("name")), "%" + farmSearchCriteria.name().toLowerCase() + "%"));
        }

        if (farmSearchCriteria.localization() != null && !farmSearchCriteria.localization().isBlank()) {
            predicates.add(cb.like(cb.lower(farm.get("localization")),
                    "%" + farmSearchCriteria.localization().toLowerCase() + "%"));
        }

        if (farmSearchCriteria.minSurface() != null) {
            predicates.add(cb.greaterThanOrEqualTo(farm.get("surface"), farmSearchCriteria.minSurface()));
        }

        if (farmSearchCriteria.maxSurface() != null) {
            predicates.add(cb.lessThanOrEqualTo(farm.get("surface"), farmSearchCriteria.maxSurface()));
        }

        if (farmSearchCriteria.createdBefore() != null) {
            predicates.add(cb.lessThanOrEqualTo(farm.get("creationDate"), farmSearchCriteria.createdBefore()));
        }

        if (farmSearchCriteria.createdAfter() != null) {
            predicates.add(cb.greaterThanOrEqualTo(farm.get("creationDate"), farmSearchCriteria.createdAfter()));
        }

        query.where(cb.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();

    }

}
