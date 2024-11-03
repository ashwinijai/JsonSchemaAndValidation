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
@Table(name = "AUTH_PERSONS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizedPersons {
    @Column(name = "CLIENT_NUM")
    private String clientNumber;
    @Id
    @Column(name = "AUTH_PERSON_ID")
    private Long authPersonId;
    @Column(name = "SUR_NAME")
    private String surName;
    @Column(name = "GIVEN_NAME")
    private String givenName;
    @Column(name = "OTHER_NAME")
    private String otherNameInitial;
}
