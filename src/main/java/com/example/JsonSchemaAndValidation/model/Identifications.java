package com.example.JsonSchemaAndValidation.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Identifications {
    @NotNull
    public TypeCodeEnum typeCode;
    @Size(max = 100)
    public String number;
    @Size(max = 2)
    public String jurisdictionOfIssueCountryCode;
    @Size(max = 2)
    public String jurisdictionOfIssueProvinceStateCode;
}
