package com.capitole.ecommerce.domain.model.price.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
public class BrandDTO {
	private Long id;
	@JsonIgnore
	private String name;
}
