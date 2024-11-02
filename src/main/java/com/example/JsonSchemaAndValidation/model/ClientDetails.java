package com.example.JsonSchemaAndValidation.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientDetails {
    @NotNull
    @Size(max = 10)
    public String clientNumber;
    @NotNull
    @Size(max = 20)
    public String customerNumber;
    @Size(max = 100)
    public String nameOfEntity;
    public Address address;
    @Size(max = 200)
    public String natureOfPrincipalBusiness;
    public boolean registrationIncorporationIndicator;
    @NotNull
    public List<RegistrationsIncorporations> registrationIncorporations;
    @NotNull
    public List<Identifications> identifications;
    @NotNull
    public List<AuthorizedPersons> authorizedPersons;
}
