package com.example.JsonSchemaAndValidation.repo;

import com.example.JsonSchemaAndValidation.entity.AuthorizedPersons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorizedPersonsRepository extends JpaRepository<AuthorizedPersons, Long> {
    @Query("select u from AuthorizedPersons u where u.clientNumber = :clientNum")
    List<AuthorizedPersons> findAuthByClientNum(String clientNum);
}
