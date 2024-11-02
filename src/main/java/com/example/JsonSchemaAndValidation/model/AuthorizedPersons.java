package com.example.JsonSchemaAndValidation.model;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorizedPersons {
    @Size(max = 100)
    public String surName;
    @Size(max = 100)
    public String givenName;
    @Size(max = 100)
    public String otherNameInitial;
}
