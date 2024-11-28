package systems.citronix.demo.repository;

import java.util.List;

import systems.citronix.demo.dto.request.FarmSearchCriteria;
import systems.citronix.demo.model.Farm;

public interface FarmRepositoryCustom {
    
    List<Farm> searchFarm(FarmSearchCriteria farmSearchCriteria);
}
