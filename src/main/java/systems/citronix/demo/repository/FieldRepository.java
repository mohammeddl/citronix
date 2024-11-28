package systems.citronix.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import systems.citronix.demo.model.Field;

public interface FieldRepository extends JpaRepository<Field, Long> {
    long countByFarmId(Long farmId);
    List<Field> findAllByFarmId(Long farmId);
    boolean existsByFarmId(Long farmId);

}
