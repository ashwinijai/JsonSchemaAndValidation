package com.example.JsonSchemaAndValidation.model;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizedPersons {
    @Size(max = 100)
    private String surName;
    @Size(max = 100)
    private String givenName;
    @Size(max = 100)
    private String otherNameInitial;
}
