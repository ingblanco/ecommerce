package com.capitole.ecommerce.domain.model.price.dto;

import java.time.LocalDateTime;

import com.capitole.ecommerce.infrastructure.adapter.entity.Price;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(value = "PriceDTO", description = "Represents the exit of the service")
public class PriceDTO {
	@JsonProperty(value = "brandId")
	@ApiModelProperty(value = "Brand identification number.", example = "1")
	private Long brandId;
	@JsonProperty(value = "startDate")
	@ApiModelProperty(value = "initial date range.", example = "2020-06-15T00:00:00")
	private LocalDateTime startDate;
	@JsonProperty(value = "endDate")
	@ApiModelProperty(value = "final date range.", example = "2020-06-15T11:00:00")
	private LocalDateTime endDate;
	@JsonProperty(value = "priceList")
	@ApiModelProperty(value = "Applicable pricing rate identifier.", example = "1")
	private Long priceList;
	@JsonProperty(value = "productId")
	@ApiModelProperty(value = "Product identification number.", example = "35455")
	private Long productId;
	@JsonProperty(value = "price")
	@ApiModelProperty(value = "Final sale price.", example = "35.55")
	private Double price;

	
	public static PriceDTO toDTO(Price price) {
		var priceDTO = new PriceDTO();
		if(price!=null) {
			if(price.getBrand()!=null)
				priceDTO.setBrandId(price.getBrand().getId());
			priceDTO.setEndDate(price.getEndDate());
			priceDTO.setStartDate(price.getStartDate());
			priceDTO.setPrice(price.getPrice());
			priceDTO.setPriceList(price.getPriceList());
			priceDTO.setProductId(price.getProductId());
				
		}
		return priceDTO;
	}
}
