package com.capitole.ecommerce.infrastructure.rest.controller.price;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.capitole.ecommerce.application.usescases.PriceUseCase;
import com.capitole.ecommerce.domain.model.price.dto.PriceDTO;
import com.capitole.ecommerce.domain.model.price.dto.request.RequestPriceDTO;
import com.capitole.ecommerce.infrastructure.constants.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PriceUseCase priceUseCase;

	@Test
	void testRequestReturnsPriceForGivenDateTime() throws Exception {
		testPriceForGivenDateTime(convertStringToLocalDataTime("2020-06-14 10:00:00"), 35.50);
		testPriceForGivenDateTime(convertStringToLocalDataTime("2020-06-14 16:00:00"), 25.45);
		testPriceForGivenDateTime(convertStringToLocalDataTime("2020-06-14 21:00:00"), 25.45);
		testPriceForGivenDateTime(convertStringToLocalDataTime("2020-06-15 10:00:00"), 38.95);
		testPriceForGivenDateTime(convertStringToLocalDataTime("2020-06-16 21:00:00"), 38.95);
	}

	private void testPriceForGivenDateTime(LocalDateTime applicationDate, double expectedPrice) throws Exception {
		var request = new RequestPriceDTO();

		request.setBrandId(1L);
		request.setProductId(35455L);
		request.setApplicationDate(applicationDate);

		PriceDTO priceDTO = new PriceDTO();
		priceDTO.setBrandId(1L);
		priceDTO.setPrice(expectedPrice);
		when(priceUseCase.getPriceByBrandProductAndApplicationDate(anyLong(), anyLong(), any(LocalDateTime.class)))
				.thenReturn(Optional.of(priceDTO));

		mockMvc.perform(post("/api/v1/price").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
				.andExpect(jsonPath("$.brandId").value(1L)).andExpect(jsonPath("$.price").value(expectedPrice));
	}

	private LocalDateTime convertStringToLocalDataTime(String date) {
		var formatter = DateTimeFormatter.ofPattern(Constants.FORMAT_DATE);
		return LocalDateTime.parse(date, formatter);
	}

}