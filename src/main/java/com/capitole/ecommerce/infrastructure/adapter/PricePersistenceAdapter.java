package com.capitole.ecommerce.infrastructure.adapter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capitole.ecommerce.domain.model.price.dto.PriceDTO;
import com.capitole.ecommerce.domain.port.PricePersistencePort;
import com.capitole.ecommerce.infrastructure.adapter.entity.Price;
import com.capitole.ecommerce.infrastructure.adapter.repository.PriceRepository;

@Service
public class PricePersistenceAdapter implements PricePersistencePort {

	private final PriceRepository priceRepository;

	public PricePersistenceAdapter(PriceRepository priceRepository) {

		this.priceRepository = priceRepository;
	}

	@Override
	public Optional<PriceDTO> getPriceByBrandProductAndApplicationDate(Long brandId, Long productId,
			LocalDateTime applicationDate) {

		return getPrice(priceRepository.findByBrandProductAndApplicationDate(brandId, productId, applicationDate));

	}

	private Optional<PriceDTO> getPrice(List<Price> prices) {
		return prices.stream().max(Comparator.comparing(Price::getPriority)).map(PriceDTO::toDTO);
	}

}
