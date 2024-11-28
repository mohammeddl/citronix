package systems.citronix.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import systems.citronix.demo.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    
}
