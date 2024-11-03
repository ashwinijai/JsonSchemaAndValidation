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
@Table(name = "ADDRESS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Column(name = "CLIENT_NUM")
    private String clientNumber;
    @Id
    @Column(name = "ADDR_ID")
    private Long addrId;
    @Column(name = "ADDR_LINE1")
    private String addrLine1;
    @Column(name = "ADDR_LINE2")
    private String addrLine2;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STATE")
    private String stateCode;
    @Column(name = "COUNTRY")
    private String countryCode;
}
