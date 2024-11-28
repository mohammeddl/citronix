package systems.citronix.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import systems.citronix.demo.model.Harvest;
import systems.citronix.demo.model.Season;

public interface HarvestRepository extends JpaRepository<Harvest, Long> {
    boolean existsBySeasonAndFieldId(Season season, Long fieldId);
}
