package com.capitole.ecommerce.infrastructure.adapter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import com.capitole.ecommerce.domain.model.price.dto.PriceDTO;
import com.capitole.ecommerce.infrastructure.adapter.entity.Price;
import com.capitole.ecommerce.infrastructure.adapter.repository.PriceRepository;

class PricePersistenceAdapterTest {
    @Test
    void testGetPriceByBrandProductAndApplicationDateReturnsOptionalEmptyWhenNoPriceFound() {
        // Given
    	var priceRepository = mock(PriceRepository.class);

        //When
        when(priceRepository.findByBrandProductAndApplicationDate(anyLong(), anyLong(), any(LocalDateTime.class)))
                .thenReturn(List.of());

        var pricePersistenceAdapter = new PricePersistenceAdapter(priceRepository);

        // Then
        var result = pricePersistenceAdapter.getPriceByBrandProductAndApplicationDate(1L, 12345L, LocalDateTime.now());

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetPriceByBrandProductAndApplicationDateReturnsPriceDTOWhenPriceFound() {
        // Given
    	var priceRepository = mock(PriceRepository.class);
    	var price = new Price();
        //When
        when(priceRepository.findByBrandProductAndApplicationDate(anyLong(), anyLong(), any(LocalDateTime.class)))
                .thenReturn(List.of(price));

        var pricePersistenceAdapter = new PricePersistenceAdapter(priceRepository);

        // Then
        var result = pricePersistenceAdapter.getPriceByBrandProductAndApplicationDate(1L, 12345L, LocalDateTime.now());

        // Assert
        assertTrue(result.isPresent());


    }
}