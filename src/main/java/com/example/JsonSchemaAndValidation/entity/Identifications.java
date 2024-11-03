package com.example.JsonSchemaAndValidation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "IDENTIFICATIONS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Identifications {
    @Column(name = "CLIENT_NUM")
    private String clientNumber;
    @Id
    @Column(name = "IDENTIFICATION_ID")
    private Long identificationId;
    @Column(name = "TYPE_CODE")
    private String typeCode;
    @Column(name = "NUMBER")
    private String number;
    @Column(name = "JUR_COUNTRY")
    private String jurisdictionOfIssueCountryCode;
    @Column(name = "JUR_STATE")
    private String jurisdictionOfIssueProvinceStateCode;
}
