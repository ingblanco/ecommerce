package com.capitole.ecommerce.infrastructure.handler.excepctions.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ResponseError {
    @JsonProperty(value = "typeError")
    @ApiModelProperty(value = "Type of error", example = "Validation Error", position = 1, required = true)
    private String typeError;
    @JsonProperty(value = "code")
    @ApiModelProperty(value = "Code of error", example = "2", position = 2, required = true)
    private Integer code;
    @JsonProperty(value = "detail")
    @ApiModelProperty(value = "Details of error", example = "Invalid format in request fields", position = 3, required = true)
    private String detail;
    @JsonProperty(value = "errors")
    @ApiModelProperty(value = "List of validations errors on request fields", example = "The personId must be greater than zero", position = 4, required = true)
    private List<String> errors;

}
