package com.capitole.ecommerce.infrastructure.handler.excepctions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;

import com.capitole.ecommerce.infrastructure.constants.Constants;
import com.capitole.ecommerce.infrastructure.constants.ErrorType;

public class GlobalExceptionHandlerTest {

	@Test
	public void testHandleUnexpectedTypeException() {
		var handler = new GlobalExceptionHandler();
		var e = new DateTimeParseException("Invalid date format", "", 0);

		var response = handler.handleUnexpectedTypeException(e);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(ErrorType.VALIDATION_ERROR.getTypeError(), response.getBody().getTypeError());
		assertEquals(ErrorType.VALIDATION_ERROR.getCode(), response.getBody().getCode());
		assertEquals(Constants.INVALID_DATE_FORMAT + Constants.FORMAT_DATE, response.getBody().getDetail());
	}

	@Test
	public void testHandleDataAccessException() {
		var handler = new GlobalExceptionHandler();
		var dae = new DataAccessException("DB error") {
		};

		var response = handler.handleDataAccesException(dae);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals(ErrorType.BUSINESS_ERROR.getTypeError(), response.getBody().getTypeError());
		assertEquals(ErrorType.BUSINESS_ERROR.getCode(), response.getBody().getCode());
		assertEquals(Constants.BD_ERROR, response.getBody().getDetail());
	}

	@Test
	public void testHandleGenericException() {
		var handler = new GlobalExceptionHandler();
		var e = new Exception("Generic error");

		var response = handler.handle(e);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals(ErrorType.TECHNICAL_ERROR.getTypeError(), response.getBody().getTypeError());
		assertEquals(ErrorType.TECHNICAL_ERROR.getCode(), response.getBody().getCode());
		assertNull(null, response.getBody().getDetail());
	}

}
