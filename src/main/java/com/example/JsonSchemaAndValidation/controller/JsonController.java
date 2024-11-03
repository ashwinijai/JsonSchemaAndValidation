package com.example.JsonSchemaAndValidation.controller;

import com.example.JsonSchemaAndValidation.Service.JsonService;
import com.example.JsonSchemaAndValidation.model.MsgDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.module.jakarta.validation.JakartaValidationModule;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.github.victools.jsonschema.generator.OptionPreset.PLAIN_JSON;
import static com.github.victools.jsonschema.generator.SchemaVersion.DRAFT_2020_12;
import static com.github.victools.jsonschema.module.jakarta.validation.JakartaValidationOption.INCLUDE_PATTERN_EXPRESSIONS;
import static com.github.victools.jsonschema.module.jakarta.validation.JakartaValidationOption.NOT_NULLABLE_FIELD_IS_REQUIRED;

@RestController
public class JsonController {
    @Autowired
    JsonService jsonService;
    @GetMapping("/getJsonSchema")
    public ResponseEntity<String> getJsonSchema() throws JsonProcessingException {
        return new ResponseEntity<>(jsonService.getJsonSchema(), HttpStatus.OK);
    }

    @PostMapping("/validateJson")
    public ResponseEntity<String> validateJson(@RequestBody JsonNode jsonNode){
      Set<ValidationMessage> validationMessageSet = jsonService.validateJson(jsonNode);
        if(validationMessageSet.isEmpty()) {
            return new ResponseEntity<>("Validation completed successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(validationMessageSet.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/validateDBJson")
    public ResponseEntity<String> validateDBJson(@RequestParam("msgId") String msgId){
        Set<ValidationMessage> validationMessageSet = jsonService.validateDBJson(msgId);
        if(validationMessageSet.isEmpty()) {
            return new ResponseEntity<>("Validation completed successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(validationMessageSet.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
