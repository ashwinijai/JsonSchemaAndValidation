package com.example.JsonSchemaAndValidation.controller;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static com.github.victools.jsonschema.generator.OptionPreset.PLAIN_JSON;
import static com.github.victools.jsonschema.generator.SchemaVersion.DRAFT_2020_12;
import static com.github.victools.jsonschema.module.jakarta.validation.JakartaValidationOption.INCLUDE_PATTERN_EXPRESSIONS;
import static com.github.victools.jsonschema.module.jakarta.validation.JakartaValidationOption.NOT_NULLABLE_FIELD_IS_REQUIRED;

@RestController
public class JsonController {

    @GetMapping("/getJsonSchema")
    public ResponseEntity<String> getJsonSchema() throws JsonProcessingException {
        JakartaValidationModule module = new JakartaValidationModule(NOT_NULLABLE_FIELD_IS_REQUIRED, INCLUDE_PATTERN_EXPRESSIONS);
        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(DRAFT_2020_12, PLAIN_JSON).with(module);
        SchemaGenerator generator = new SchemaGenerator(configBuilder.build());
        JsonNode jsonSchema = generator.generateSchema(MsgDetails.class);
        ObjectMapper mapper = new ObjectMapper();
        Object json = mapper.readValue(jsonSchema.toString(), Object.class);
        return new ResponseEntity<>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json), HttpStatus.OK);
    }

    @PostMapping("/validateJson")
    public ResponseEntity<String> validateJson(@RequestBody JsonNode jsonNode){
       JsonSchema schema =  JsonSchemaFactory.getInstance( SpecVersion.VersionFlag.V7 ).getSchema("{\n" +
               "  \"$schema\" : \"https://json-schema.org/draft/2020-12/schema\",\n" +
               "  \"$defs\" : {\n" +
               "    \"TypeCodeEnum\" : {\n" +
               "      \"type\" : \"string\",\n" +
               "      \"enum\" : [ \"Incorporated\", \"Registered\", \"IncorporatedAndRegistered\", \"Unknown\" ]\n" +
               "    }\n" +
               "  },\n" +
               "  \"type\" : \"object\",\n" +
               "  \"properties\" : {\n" +
               "    \"clientDetails\" : {\n" +
               "      \"type\" : \"array\",\n" +
               "      \"items\" : {\n" +
               "        \"type\" : \"object\",\n" +
               "        \"properties\" : {\n" +
               "          \"address\" : {\n" +
               "            \"type\" : \"object\",\n" +
               "            \"properties\" : {\n" +
               "              \"addrLine1\" : {\n" +
               "                \"type\" : \"string\",\n" +
               "                \"maxLength\" : 100\n" +
               "              },\n" +
               "              \"addrLine2\" : {\n" +
               "                \"type\" : \"string\",\n" +
               "                \"maxLength\" : 100\n" +
               "              },\n" +
               "              \"city\" : {\n" +
               "                \"type\" : \"string\",\n" +
               "                \"maxLength\" : 100\n" +
               "              },\n" +
               "              \"countryCode\" : {\n" +
               "                \"type\" : \"string\",\n" +
               "                \"maxLength\" : 2\n" +
               "              },\n" +
               "              \"stateCode\" : {\n" +
               "                \"type\" : \"string\",\n" +
               "                \"maxLength\" : 2\n" +
               "              }\n" +
               "            }\n" +
               "          },\n" +
               "          \"authorizedPersons\" : {\n" +
               "            \"type\" : \"array\",\n" +
               "            \"items\" : {\n" +
               "              \"type\" : \"object\",\n" +
               "              \"properties\" : {\n" +
               "                \"givenName\" : {\n" +
               "                  \"type\" : \"string\",\n" +
               "                  \"maxLength\" : 100\n" +
               "                },\n" +
               "                \"otherNameInitial\" : {\n" +
               "                  \"type\" : \"string\",\n" +
               "                  \"maxLength\" : 100\n" +
               "                },\n" +
               "                \"surName\" : {\n" +
               "                  \"type\" : \"string\",\n" +
               "                  \"maxLength\" : 100\n" +
               "                }\n" +
               "              }\n" +
               "            }\n" +
               "          },\n" +
               "          \"clientNumber\" : {\n" +
               "            \"type\" : \"string\",\n" +
               "            \"maxLength\" : 10\n" +
               "          },\n" +
               "          \"customerNumber\" : {\n" +
               "            \"type\" : \"string\",\n" +
               "            \"maxLength\" : 20\n" +
               "          },\n" +
               "          \"identifications\" : {\n" +
               "            \"type\" : \"array\",\n" +
               "            \"items\" : {\n" +
               "              \"type\" : \"object\",\n" +
               "              \"properties\" : {\n" +
               "                \"jurisdictionOfIssueCountryCode\" : {\n" +
               "                  \"type\" : \"string\",\n" +
               "                  \"maxLength\" : 2\n" +
               "                },\n" +
               "                \"jurisdictionOfIssueProvinceStateCode\" : {\n" +
               "                  \"type\" : \"string\",\n" +
               "                  \"maxLength\" : 2\n" +
               "                },\n" +
               "                \"number\" : {\n" +
               "                  \"type\" : \"string\",\n" +
               "                  \"maxLength\" : 100\n" +
               "                },\n" +
               "                \"typeCode\" : {\n" +
               "                  \"$ref\" : \"#/$defs/TypeCodeEnum\"\n" +
               "                }\n" +
               "              },\n" +
               "              \"required\" : [ \"typeCode\" ]\n" +
               "            }\n" +
               "          },\n" +
               "          \"nameOfEntity\" : {\n" +
               "            \"type\" : \"string\",\n" +
               "            \"maxLength\" : 100\n" +
               "          },\n" +
               "          \"natureOfPrincipalBusiness\" : {\n" +
               "            \"type\" : \"string\",\n" +
               "            \"maxLength\" : 200\n" +
               "          },\n" +
               "          \"registrationIncorporationIndicator\" : {\n" +
               "            \"type\" : \"boolean\"\n" +
               "          },\n" +
               "          \"registrationIncorporations\" : {\n" +
               "            \"type\" : \"array\",\n" +
               "            \"items\" : {\n" +
               "              \"type\" : \"object\",\n" +
               "              \"properties\" : {\n" +
               "                \"identifierTypeCode\" : {\n" +
               "                  \"$ref\" : \"#/$defs/TypeCodeEnum\"\n" +
               "                },\n" +
               "                \"jurisdictionOfIssueCountryCode\" : {\n" +
               "                  \"type\" : \"string\",\n" +
               "                  \"maxLength\" : 2\n" +
               "                },\n" +
               "                \"jurisdictionOfIssueProvinceStateCode\" : {\n" +
               "                  \"type\" : \"string\",\n" +
               "                  \"maxLength\" : 2\n" +
               "                },\n" +
               "                \"number\" : {\n" +
               "                  \"type\" : \"string\",\n" +
               "                  \"maxLength\" : 100\n" +
               "                }\n" +
               "              },\n" +
               "              \"required\" : [ \"identifierTypeCode\" ]\n" +
               "            }\n" +
               "          }\n" +
               "        },\n" +
               "        \"required\" : [ \"authorizedPersons\", \"clientNumber\", \"customerNumber\", \"identifications\", \"registrationIncorporations\" ]\n" +
               "      }\n" +
               "    },\n" +
               "    \"msgDateTimestamp\" : {\n" +
               "      \"type\" : \"string\",\n" +
               "      \"format\" : \"date-time\"\n" +
               "    },\n" +
               "    \"msgID\" : {\n" +
               "      \"type\" : \"string\"\n" +
               "    },\n" +
               "    \"sourceSystem\" : {\n" +
               "      \"type\" : \"string\",\n" +
               "      \"enum\" : [ \"FLEXCUBE\", \"LIQ\" ]\n" +
               "    }\n" +
               "  },\n" +
               "  \"required\" : [ \"clientDetails\", \"msgID\", \"sourceSystem\" ]\n" +
               "}");
       Set<ValidationMessage> validationMessageSet = schema.validate(jsonNode);
        if(validationMessageSet.isEmpty()) {
            return new ResponseEntity("Validation completed successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity(validationMessageSet.toString(), HttpStatus.BAD_REQUEST);
        }

    }
}
