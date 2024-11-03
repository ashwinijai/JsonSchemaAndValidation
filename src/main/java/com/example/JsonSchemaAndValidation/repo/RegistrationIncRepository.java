package com.example.JsonSchemaAndValidation.repo;

import com.example.JsonSchemaAndValidation.entity.Address;
import com.example.JsonSchemaAndValidation.entity.RegistrationInc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationIncRepository extends JpaRepository<RegistrationInc, Long> {

    @Query("select u from RegistrationInc u where u.clientNumber = :clientNum")
    List<RegistrationInc> findRegistrationIncByClientNum(String clientNum);
}
