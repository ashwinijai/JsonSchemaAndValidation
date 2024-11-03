package com.example.JsonSchemaAndValidation.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Size(max = 100)
    private String addrLine1;
    @Size(max = 100)
    private String addrLine2;
    @Size(max = 100)
    private String city;
    @Size(max = 2)
    private String stateCode;
    @Size(max = 2)
    public String countryCode;
}
