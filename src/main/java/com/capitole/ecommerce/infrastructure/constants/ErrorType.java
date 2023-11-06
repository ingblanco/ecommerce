package com.capitole.ecommerce.infrastructure.constants;

public enum ErrorType {
    TECHNICAL_ERROR("Technical Error", 1),
    VALIDATION_ERROR("Validation Error", 2),
    BUSINESS_ERROR("Business Error", 3);
    private  String typeError;
    private Integer code;
    ErrorType(String typeError, Integer code) {
        this.typeError = typeError;
        this.code = code;
    }
    public String getTypeError() {
        return typeError;
    }
    public Integer getCode() {
        return code;
    }
}
