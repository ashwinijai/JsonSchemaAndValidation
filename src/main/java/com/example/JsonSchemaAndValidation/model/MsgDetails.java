package com.example.JsonSchemaAndValidation.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class MsgDetails {
    @NotNull
    public String msgID;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "YYYY-MM-DDTHH:MM:SS+ZZ:ZZ")
    @NotNull
    public Date msgDateTimestamp;
    @NotNull
    public SourceSystemEnum sourceSystem;
    @NotNull
    public List<ClientDetails> clientDetails;
}
