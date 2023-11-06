package com.capitole.ecommerce.domain.port;

import com.capitole.ecommerce.domain.model.price.dto.PriceDTO;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PricePersistencePort {
    Optional<PriceDTO> getPriceByBrandProductAndApplicationDate(Long brandId, Long productId, LocalDateTime applicationDate);
}
