package com.capitole.ecommerce.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.time.LocalDateTime;

import com.capitole.ecommerce.domain.port.PricePersistencePort;
import org.junit.jupiter.api.Test;
import com.capitole.ecommerce.domain.model.price.dto.PriceDTO;


class PriceServiceTest {
    @Test
    void testGetPriceByBrandProductAndApplicationDateReturnsOptionalEmptyWhenNoPriceFound() {

        //Given
        var pricePersistencePort = mock(PricePersistencePort.class);

        //When
        when(pricePersistencePort.getPriceByBrandProductAndApplicationDate(anyLong(), anyLong(), any(LocalDateTime.class)))
                .thenReturn(Optional.empty());

        var priceService = new PriceService(pricePersistencePort);

        //then
        var result = priceService.getPriceByBrandProductAndApplicationDate(1L, 12345L, LocalDateTime.now());
        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetPriceByBrandProductAndApplicationDateReturnsPriceDTOWhenPriceFound() {
        //Given
    	var pricePersistencePort = mock(PricePersistencePort.class);
    	var expectedPriceDTO = new PriceDTO();
        //When
        when(pricePersistencePort.getPriceByBrandProductAndApplicationDate(anyLong(), anyLong(), any(LocalDateTime.class)))
                .thenReturn(Optional.of(expectedPriceDTO));

        var priceService = new PriceService(pricePersistencePort);

        //Then
        var result = priceService.getPriceByBrandProductAndApplicationDate(1L, 12345L, LocalDateTime.now());

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedPriceDTO, result.get());
    }
}