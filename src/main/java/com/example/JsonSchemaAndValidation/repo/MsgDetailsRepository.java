package com.example.JsonSchemaAndValidation.repo;

import com.example.JsonSchemaAndValidation.entity.ClientDetails;
import com.example.JsonSchemaAndValidation.entity.MsgDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MsgDetailsRepository extends JpaRepository<MsgDetails, String> {
    @Query("select u from MsgDetails u where u.msgId = :msgId")
    MsgDetails findMsgDetailsByMsgId(String msgId);
}
