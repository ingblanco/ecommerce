package com.capitole.ecommerce.infrastructure.rest.controller.price;


import javax.validation.Valid;

import com.capitole.ecommerce.infrastructure.constants.Constants;
import com.capitole.ecommerce.infrastructure.handler.excepctions.response.ResponseError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capitole.ecommerce.application.usescases.PriceUseCase;
import com.capitole.ecommerce.domain.model.price.dto.PriceDTO;
import com.capitole.ecommerce.domain.model.price.dto.request.RequestPriceDTO;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Prices")
public class PriceController {
    private final PriceUseCase priceUseCase;

    public PriceController(PriceUseCase priceUseCase) {
        this.priceUseCase = priceUseCase;
    }


    @PostMapping(path = "/price", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return price information", notes = "Retrieve price information based on brand ID, product ID, and application date.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = PriceDTO.class),
            @ApiResponse(code = 400, message = Constants.INVALID_INPUTS_REQUEST, response = ResponseError.class),
            @ApiResponse(code = 500, message = Constants.INTERNAL_ERROR, response = ResponseError.class)
    })
    public ResponseEntity<PriceDTO> getPriceByBrandAndDateApplication(
            @Valid @RequestBody RequestPriceDTO request) {

        return priceUseCase.getPriceByBrandProductAndApplicationDate(request.getBrandId(), request.getProductId(), request.getApplicationDate()).map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));

    }
}