package com.example.JsonSchemaAndValidation.repo;

import com.example.JsonSchemaAndValidation.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("select u from Address u where u.clientNumber = :clientNum")
    Address findAddressByClientNum(String clientNum);
}
