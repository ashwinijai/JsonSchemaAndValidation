package com.example.JsonSchemaAndValidation.repo;

import com.example.JsonSchemaAndValidation.entity.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDetailsRepository extends JpaRepository<ClientDetails, String> {

    @Query("select u from ClientDetails u where u.msgId = :msgId")
    List<ClientDetails> findClientByMsgId(String msgId);
}
