package systems.citronix.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import systems.citronix.demo.model.Farm;

public interface FarmRepository extends JpaRepository<Farm, Long>, FarmRepositoryCustom {
}