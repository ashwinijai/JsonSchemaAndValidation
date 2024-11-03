package com.example.JsonSchemaAndValidation.entity;


import com.example.JsonSchemaAndValidation.model.SourceSystemEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MSG_DETAILS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MsgDetails {
    @Id
    @Column(name = "MSG_ID")
    private String msgId;
    @Column(name = "MSG_DATETIME")
    private String msgDateTimestamp;
    @Column(name = "SOURCE_SYS")
    private String sourceSystem;
}
