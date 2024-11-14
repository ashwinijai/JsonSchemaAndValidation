package com.example.JsonSchemaAndValidation.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.ToString;

public enum TypeCodeEnum {

    INCORPORATED("Incorporated"),
    REGISTERED("Registered"),
    INCORPORATED_AND_REGISTERED("Incorporated and Registered"),
    UNKNOWN("Unknown");

    private final String typeCode;

    TypeCodeEnum(String typeCode) {
        this.typeCode = typeCode;
    }

    public String toString(){
        return typeCode;
    }
}