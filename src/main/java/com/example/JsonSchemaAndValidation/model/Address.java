package com.example.JsonSchemaAndValidation.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    @Size(max = 100)
    public String addrLine1;
    @Size(max = 100)
    public String addrLine2;
    @Size(max = 100)
    public String city;
    @Size(max = 2)
    public String stateCode;
    @Size(max = 2)
    public String countryCode;
}
