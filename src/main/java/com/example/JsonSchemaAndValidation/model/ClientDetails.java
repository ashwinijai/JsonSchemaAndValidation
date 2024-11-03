package com.example.JsonSchemaAndValidation.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ClientDetails {
    @NotNull
    @Size(max = 10)
    private String clientNumber;
    @NotNull
    @Size(max = 20)
    private String customerNumber;
    @Size(max = 100)
    private String nameOfEntity;
    @NotNull
    private Address address;
    @Size(max = 200)
    private String natureOfPrincipalBusiness;
    private boolean registrationIncorporationIndicator;
    @NotNull
    private List<RegistrationsIncorporations> registrationIncorporations;
    @NotNull
    private List<Identifications> identifications;
    @NotNull
    private List<AuthorizedPersons> authorizedPersons;
}
