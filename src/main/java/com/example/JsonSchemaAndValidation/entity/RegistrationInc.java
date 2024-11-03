package com.example.JsonSchemaAndValidation.entity;

import com.example.JsonSchemaAndValidation.model.TypeCodeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "REGISTRATION_INC")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationInc {

    @Column(name = "CLIENT_NUM")
    private String clientNumber;
    @Id
    @Column(name = "REG_INC_ID")
    private Long regIncId;
    @Column(name = "TYPE_CODE")
    private String identifierTypeCode;
    @Column(name = "NUMBER")
    private String number;
    @Column(name = "JUR_COUNTRY")
    private String jurisdictionOfIssueCountryCode;
    @Column(name = "JUR_STATE")
    private String jurisdictionOfIssueProvinceStateCode;
}
