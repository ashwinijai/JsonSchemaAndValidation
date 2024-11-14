package com.example.JsonSchemaAndValidation.controller;

import com.example.JsonSchemaAndValidation.Service.JsonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.ValidationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
            List<String> stringList =new ArrayList<>();
            validationMessageSet.forEach(m -> {
                if(m.getMessage().contains("$.msgDateTimestamp:")){
                    stringList.add("$.msgDateTimestamp: Date Timestamp doesn't match the pattern yyyy-MM-ddThh:mm:ss+-ZZ:ZZ");
                }
                else{
                    stringList.add(m.getMessage());
                }
            });
            return new ResponseEntity<>(stringList.toString(), HttpStatus.BAD_REQUEST);
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
