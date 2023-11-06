package com.capitole.ecommerce.application.usescases;

import com.capitole.ecommerce.domain.model.price.dto.PriceDTO;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public interface PriceUseCase {
    Optional<PriceDTO> getPriceByBrandProductAndApplicationDate(Long brandId, Long productId, LocalDateTime applicationDate);
}
