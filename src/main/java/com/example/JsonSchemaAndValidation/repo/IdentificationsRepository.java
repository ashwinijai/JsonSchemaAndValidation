package com.example.JsonSchemaAndValidation.repo;

import com.example.JsonSchemaAndValidation.entity.Address;
import com.example.JsonSchemaAndValidation.entity.Identifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdentificationsRepository extends JpaRepository<Identifications, Long> {

    @Query("select u from Identifications u where u.clientNumber = :clientNum")
    List<Identifications> findIdentificationsByClientNum(String clientNum);
}
