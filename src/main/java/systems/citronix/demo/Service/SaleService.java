package systems.citronix.demo.Service;

import java.util.List;

import systems.citronix.demo.dto.request.SaleRequestDTO;
import systems.citronix.demo.dto.response.SaleResponseDTO;

public interface SaleService {
    SaleResponseDTO createSale(SaleRequestDTO saleDTO);
    List<SaleResponseDTO> getAllSales();
    SaleResponseDTO updateSale(Long id, SaleRequestDTO saleDTO);
    void deleteSale(Long id);


    
} 
