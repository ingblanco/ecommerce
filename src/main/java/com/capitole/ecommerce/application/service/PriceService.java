package com.capitole.ecommerce.application.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capitole.ecommerce.application.usescases.PriceUseCase;
import com.capitole.ecommerce.domain.model.price.dto.PriceDTO;
import com.capitole.ecommerce.domain.port.PricePersistencePort;

@Service
public class PriceService implements PriceUseCase {

    private final PricePersistencePort pricePersistencePort;
 
    public PriceService(PricePersistencePort pricePersistencePort){
        this.pricePersistencePort = pricePersistencePort;
    }
    @Override
    public Optional<PriceDTO> getPriceByBrandProductAndApplicationDate(Long brandId, Long productId, LocalDateTime applicationDate) {
        return pricePersistencePort.getPriceByBrandProductAndApplicationDate(brandId,productId,applicationDate);
    }
}
