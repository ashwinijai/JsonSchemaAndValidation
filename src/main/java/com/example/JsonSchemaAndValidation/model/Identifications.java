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
public class Identifications {
    @NotNull
    private TypeCodeEnum typeCode;
    @Size(max = 100)
    private String number;
    @Size(max = 2)
    private String jurisdictionOfIssueCountryCode;
    @Size(max = 2)
    private String jurisdictionOfIssueProvinceStateCode;
}
