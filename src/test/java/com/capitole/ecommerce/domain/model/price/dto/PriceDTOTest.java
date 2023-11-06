package com.capitole.ecommerce.domain.model.price.dto;

import com.capitole.ecommerce.infrastructure.adapter.entity.Brand;
import com.capitole.ecommerce.infrastructure.adapter.entity.Price;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PriceDTOTest {
    @Test
    void testToDTOWhenPriceIsNull() {
    	var priceDTO = PriceDTO.toDTO(null);
        assertNotNull(priceDTO);
        assertEquals(priceDTO,new PriceDTO());
    }

    @Test
    void testToDTOWhenBrandIsNull() {
    	var price = new Price();
    	var priceDTO = PriceDTO.toDTO(price);
        assertNotNull(priceDTO);
        assertNull(priceDTO.getBrandId());
        assertNull(priceDTO.getStartDate());
        assertNull(priceDTO.getEndDate());
        assertNull(priceDTO.getPriceList());
        assertNull(priceDTO.getProductId());
        assertNull(priceDTO.getPrice());
    }

    @Test
    void testToDTOWhenPriceAndBrandAreNotNull() {
    	var brand = new Brand();
        brand.setId(1L);

        var price = new Price();
        price.setBrand(brand);
        price.setStartDate(LocalDateTime.of(2023, 1, 1, 0, 0));
        price.setEndDate(LocalDateTime.of(2023, 12, 31, 23, 59, 59));
        price.setPriceList(1L);
        price.setProductId(12345L);
        price.setPrice(50.0);

        var priceDTO = PriceDTO.toDTO(price);
        assertNotNull(priceDTO);
        assertEquals(1L, priceDTO.getBrandId());
        assertEquals(LocalDateTime.of(2023, 1, 1, 0, 0), priceDTO.getStartDate());
        assertEquals(LocalDateTime.of(2023, 12, 31, 23, 59, 59), priceDTO.getEndDate());
        assertEquals(1L, priceDTO.getPriceList());
        assertEquals(12345L, priceDTO.getProductId());
        assertEquals(50.0, priceDTO.getPrice());
    }
}