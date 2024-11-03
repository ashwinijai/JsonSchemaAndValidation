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
@Table(name = "CLIENT_DETAILS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDetails {
    @Column(name = "MSG_ID")
    private String msgId;
    @Id
    @Column(name = "CLIENT_NUM")
    private String clientNumber;
    @Column(name = "CUSTOMER_NUM")
    private String customerNumber;
    @Column(name = "ENTITY_NAME")
    private String nameOfEntity;
    @Column(name = "BUSS_NATURE")
    private String natureOfPrincipalBusiness;
    @Column(name="REG_INC_IND")
    private String registrationIncorporationIndicator;
}
