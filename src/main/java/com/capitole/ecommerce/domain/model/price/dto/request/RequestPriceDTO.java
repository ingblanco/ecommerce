package com.capitole.ecommerce.domain.model.price.dto.request;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import com.capitole.ecommerce.infrastructure.constants.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(value = "RequestPriceDTO", description = "Structure used to obtain a price of a brand's product for a date range")
public class RequestPriceDTO {
    @JsonProperty(value = "brandId")
    @ApiModelProperty(value = "Brand identification number", example = "1", position = 1, required = true)
    @NotNull(message = "The brandId field cannot be null or empty")
    @Min(value = 1, message = "The personId must be greater than zero")
    private Long brandId;
    @JsonProperty(value = "productId")
    @ApiModelProperty(value = "Product identification number", example = "35455", position = 2, required = true)
    @NotNull(message = "The productId field cannot be null or empty")
    @Min(value = 1, message = "The productId must be greater than zero")
    private Long productId;
    @JsonProperty(value = "applicationDate")
    @ApiModelProperty(value = "Rate application date with format : yyyy-MM-dd HH:mm:ss ", example = "2020-06-15 10:00:00", position = 3, required = true)
    @NotNull(message = "The applicationDate field cannot be null or empty")
    @DateTimeFormat(pattern = Constants.FORMAT_DATE)
    @JsonFormat(pattern = Constants.FORMAT_DATE)
    private LocalDateTime applicationDate;
}
