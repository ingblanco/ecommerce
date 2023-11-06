package com.capitole.ecommerce.infrastructure.handler.excepctions;

import com.capitole.ecommerce.infrastructure.constants.Constants;
import com.capitole.ecommerce.infrastructure.constants.ErrorType;
import com.capitole.ecommerce.infrastructure.handler.excepctions.response.ResponseError;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({DateTimeParseException.class, InvalidFormatException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ResponseError> handleUnexpectedTypeException(Exception e) {
        logError(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createResponseError(ErrorType.VALIDATION_ERROR.getTypeError(), ErrorType.VALIDATION_ERROR.getCode(), Constants.INVALID_DATE_FORMAT + Constants.FORMAT_DATE, null));

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        logError(ex);
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createResponseError(ErrorType.VALIDATION_ERROR.getTypeError(), ErrorType.VALIDATION_ERROR.getCode(), Constants.INVALID_INPUTS_REQUEST, errors));

    }


    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ResponseError> handleDataAccesException(DataAccessException dae) {
        logError(dae);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createResponseError(ErrorType.BUSINESS_ERROR.getTypeError(), ErrorType.BUSINESS_ERROR.getCode(), Constants.BD_ERROR, null));
    }

    @ExceptionHandler()
    public ResponseEntity<ResponseError> handle(Exception e) {
        logError(e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createResponseError(ErrorType.TECHNICAL_ERROR.getTypeError(), ErrorType.TECHNICAL_ERROR.getCode(), Constants.INTERNAL_ERROR, null));

    }

    private ResponseError createResponseError(String type, Integer code, String detail, List<String> errors) {
        return new ResponseError(type, code, detail, errors);
    }

    private static void logError(Exception e) {
        log.error(e.toString(), e, (Object) null);
    }
}
