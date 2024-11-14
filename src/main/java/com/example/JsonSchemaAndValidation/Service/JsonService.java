package com.example.JsonSchemaAndValidation.Service;

import com.example.JsonSchemaAndValidation.entity.*;
import com.example.JsonSchemaAndValidation.model.MsgDetails;
import com.example.JsonSchemaAndValidation.model.RegistrationsIncorporations;
import com.example.JsonSchemaAndValidation.model.SourceSystemEnum;
import com.example.JsonSchemaAndValidation.model.TypeCodeEnum;
import com.example.JsonSchemaAndValidation.repo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.victools.jsonschema.generator.Option;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.module.jakarta.validation.JakartaValidationModule;
import com.networknt.schema.*;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.github.victools.jsonschema.generator.OptionPreset.PLAIN_JSON;
import static com.github.victools.jsonschema.generator.SchemaVersion.DRAFT_2020_12;
import static com.github.victools.jsonschema.module.jakarta.validation.JakartaValidationOption.INCLUDE_PATTERN_EXPRESSIONS;
import static com.github.victools.jsonschema.module.jakarta.validation.JakartaValidationOption.NOT_NULLABLE_FIELD_IS_REQUIRED;

@Service
public class JsonService {

    @Autowired
    AddressRepository addRepo;

    @Autowired
    AuthorizedPersonsRepository authorizedPersonsRepo;

    @Autowired
    ClientDetailsRepository clientDetailsRepo;

    @Autowired
    IdentificationsRepository identificationsRepo;

    @Autowired
    MsgDetailsRepository msgDetailsRepo;

    @Autowired
    RegistrationIncRepository regisIncRepo;

    public String getJsonSchema() throws JsonProcessingException {
        JakartaValidationModule module = new JakartaValidationModule(NOT_NULLABLE_FIELD_IS_REQUIRED,INCLUDE_PATTERN_EXPRESSIONS);
        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(DRAFT_2020_12, PLAIN_JSON).with(module);
        SchemaGenerator generator = new SchemaGenerator(configBuilder.with(Option.FLATTENED_ENUMS_FROM_TOSTRING).build());
        JsonNode jsonSchema = generator.generateSchema(MsgDetails.class);
        return jsonSchema.toString();
    }

    public Set<ValidationMessage> validateJson(JsonNode jsonNode) {
        JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7).getSchema("{\n" +
                "    \"$schema\": \"https://json-schema.org/draft/2020-12/schema\",\n" +
                "    \"$defs\": {\n" +
                "        \"TypeCodeEnum\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"enum\": [\n" +
                "                \"Incorporated\",\n" +
                "                \"Registered\",\n" +
                "                \"Incorporated and Registered\",\n" +
                "                \"Unknown\"\n" +
                "            ]\n" +
                "        }\n" +
                "    },\n" +
                "    \"type\": \"object\",\n" +
                "    \"properties\": {\n" +
                "        \"clientDetails\": {\n" +
                "            \"type\": \"array\",\n" +
                "            \"items\": {\n" +
                "                \"type\": \"object\",\n" +
                "                \"properties\": {\n" +
                "                    \"address\": {\n" +
                "                        \"type\": \"object\",\n" +
                "                        \"properties\": {\n" +
                "                            \"addrLine1\": {\n" +
                "                                \"type\": \"string\",\n" +
                "                                \"maxLength\": 100\n" +
                "                            },\n" +
                "                            \"addrLine2\": {\n" +
                "                                \"type\": \"string\",\n" +
                "                                \"maxLength\": 100\n" +
                "                            },\n" +
                "                            \"city\": {\n" +
                "                                \"type\": \"string\",\n" +
                "                                \"maxLength\": 100\n" +
                "                            },\n" +
                "                            \"countryCode\": {\n" +
                "                                \"type\": \"string\",\n" +
                "                                \"maxLength\": 2\n" +
                "                            },\n" +
                "                            \"stateCode\": {\n" +
                "                                \"type\": \"string\",\n" +
                "                                \"maxLength\": 2\n" +
                "                            }\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"authorizedPersons\": {\n" +
                "                        \"type\": \"array\",\n" +
                "                        \"items\": {\n" +
                "                            \"type\": \"object\",\n" +
                "                            \"properties\": {\n" +
                "                                \"givenName\": {\n" +
                "                                    \"type\": \"string\",\n" +
                "                                    \"maxLength\": 100\n" +
                "                                },\n" +
                "                                \"otherNameInitial\": {\n" +
                "                                    \"type\": \"string\",\n" +
                "                                    \"maxLength\": 100\n" +
                "                                },\n" +
                "                                \"surName\": {\n" +
                "                                    \"type\": \"string\",\n" +
                "                                    \"maxLength\": 100\n" +
                "                                }\n" +
                "                            }\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"clientNumber\": {\n" +
                "                        \"type\": \"string\",\n" +
                "                        \"maxLength\": 10\n" +
                "                    },\n" +
                "                    \"customerNumber\": {\n" +
                "                        \"type\": \"string\",\n" +
                "                        \"maxLength\": 20\n" +
                "                    },\n" +
                "                    \"identifications\": {\n" +
                "                        \"type\": \"array\",\n" +
                "                        \"items\": {\n" +
                "                            \"type\": \"object\",\n" +
                "                            \"properties\": {\n" +
                "                                \"jurisdictionOfIssueCountryCode\": {\n" +
                "                                    \"type\": \"string\",\n" +
                "                                    \"maxLength\": 2\n" +
                "                                },\n" +
                "                                \"jurisdictionOfIssueProvinceStateCode\": {\n" +
                "                                    \"type\": \"string\",\n" +
                "                                    \"maxLength\": 2\n" +
                "                                },\n" +
                "                                \"number\": {\n" +
                "                                    \"type\": \"string\",\n" +
                "                                    \"maxLength\": 100\n" +
                "                                },\n" +
                "                                \"typeCode\": {\n" +
                "                                    \"$ref\": \"#/$defs/TypeCodeEnum\"\n" +
                "                                }\n" +
                "                            },\n" +
                "                            \"required\": [\n" +
                "                                \"typeCode\"\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"nameOfEntity\": {\n" +
                "                        \"type\": \"string\",\n" +
                "                        \"maxLength\": 100\n" +
                "                    },\n" +
                "                    \"natureOfPrincipalBusiness\": {\n" +
                "                        \"type\": \"string\",\n" +
                "                        \"maxLength\": 200\n" +
                "                    },\n" +
                "                    \"registrationIncorporationIndicator\": {\n" +
                "                        \"type\": \"boolean\"\n" +
                "                    },\n" +
                "                    \"registrationIncorporations\": {\n" +
                "                        \"type\": \"array\",\n" +
                "                        \"items\": {\n" +
                "                            \"type\": \"object\",\n" +
                "                            \"properties\": {\n" +
                "                                \"identifierTypeCode\": {\n" +
                "                                    \"$ref\": \"#/$defs/TypeCodeEnum\"\n" +
                "                                },\n" +
                "                                \"jurisdictionOfIssueCountryCode\": {\n" +
                "                                    \"type\": \"string\",\n" +
                "                                    \"maxLength\": 2\n" +
                "                                },\n" +
                "                                \"jurisdictionOfIssueProvinceStateCode\": {\n" +
                "                                    \"type\": \"string\",\n" +
                "                                    \"maxLength\": 2\n" +
                "                                },\n" +
                "                                \"number\": {\n" +
                "                                    \"type\": \"string\",\n" +
                "                                    \"maxLength\": 100\n" +
                "                                }\n" +
                "                            },\n" +
                "                            \"required\": [\n" +
                "                                \"identifierTypeCode\"\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    }\n" +
                "                },\n" +
                "                \"required\": [\n" +
                "                    \"address\",\n" +
                "                    \"authorizedPersons\",\n" +
                "                    \"clientNumber\",\n" +
                "                    \"customerNumber\",\n" +
                "                    \"identifications\",\n" +
                "                    \"registrationIncorporations\"\n" +
                "                ]\n" +
                "            }\n" +
                "        },\n" +
                "        \"msgDateTimestamp\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"format\": \"date-time\"\n" +
                "        },\n" +
                "        \"msgID\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"minLength\": 1\n" +
                "        },\n" +
                "        \"sourceSystem\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"enum\": [\n" +
                "                \"FLEXCUBE\",\n" +
                "                \"LIQ\"\n" +
                "            ]\n" +
                "        }\n" +
                "    },\n" +
                "    \"required\": [\n" +
                "        \"msgDateTimestamp\",\n" +
                "        \"msgID\",\n" +
                "        \"sourceSystem\"\n" +
                "    ]\n" +
                "}");
        return schema.validate(jsonNode);
    }

    public Set<ValidationMessage> validateDBJson(String msgId) {
        com.example.JsonSchemaAndValidation.entity.MsgDetails msgDetailsEnt = msgDetailsRepo.findMsgDetailsByMsgId(msgId);
        MsgDetails msgDetails = new MsgDetails();
        msgDetails.setMsgID(msgDetailsEnt.getMsgId());
        msgDetails.setSourceSystem(SourceSystemEnum.valueOf(msgDetailsEnt.getSourceSystem()));
        //msgDetails.setMsgDateTimestamp(LocalDateTime.parse(msgDetailsEnt.getMsgDateTimestamp(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")));
        List<ClientDetails> clientDetails = clientDetailsRepo.findClientByMsgId(msgId);
        List<com.example.JsonSchemaAndValidation.model.ClientDetails> clientList = clientDetails.stream().map(c -> {
            com.example.JsonSchemaAndValidation.model.ClientDetails clientDet = new com.example.JsonSchemaAndValidation.model.ClientDetails();
            clientDet.setClientNumber(c.getClientNumber());
            clientDet.setCustomerNumber(c.getCustomerNumber());
            clientDet.setNameOfEntity(c.getNameOfEntity());
            clientDet.setNatureOfPrincipalBusiness(c.getNatureOfPrincipalBusiness());
            clientDet.setRegistrationIncorporationIndicator(Boolean.parseBoolean(c.getRegistrationIncorporationIndicator()));
            Address addressEnt = addRepo.findAddressByClientNum(c.getClientNumber());
            List<RegistrationInc> regIncEntList = regisIncRepo.findRegistrationIncByClientNum(c.getClientNumber());
            List<Identifications> identificationEntList = identificationsRepo.findIdentificationsByClientNum(c.getClientNumber());
            List<AuthorizedPersons> authPersonEntList = authorizedPersonsRepo.findAuthByClientNum(c.getClientNumber());
            com.example.JsonSchemaAndValidation.model.Address address = new com.example.JsonSchemaAndValidation.model.Address();
            address.setAddrLine1(addressEnt.getAddrLine1());
            address.setAddrLine2(addressEnt.getAddrLine2());
            address.setCity(addressEnt.getCity());
            address.setStateCode(addressEnt.getStateCode());
            address.setCountryCode(addressEnt.getCountryCode());
            clientDet.setAddress(address);
            List<RegistrationsIncorporations> regList = regIncEntList.stream().map(r -> {
                RegistrationsIncorporations regInc = new RegistrationsIncorporations();
                regInc.setIdentifierTypeCode(TypeCodeEnum.valueOf(r.getIdentifierTypeCode()));
                regInc.setNumber(r.getNumber());
                regInc.setJurisdictionOfIssueProvinceStateCode(r.getJurisdictionOfIssueProvinceStateCode());
                regInc.setJurisdictionOfIssueCountryCode(r.getJurisdictionOfIssueCountryCode());
                return regInc;
            }).toList();
            clientDet.setRegistrationIncorporations(regList);
            List<com.example.JsonSchemaAndValidation.model.Identifications> identifications = identificationEntList.stream().map(i -> {
                com.example.JsonSchemaAndValidation.model.Identifications id = new com.example.JsonSchemaAndValidation.model.Identifications();
                id.setNumber(i.getNumber());
                id.setJurisdictionOfIssueProvinceStateCode(i.getJurisdictionOfIssueProvinceStateCode());
                id.setTypeCode(TypeCodeEnum.valueOf(i.getTypeCode()));
                id.setJurisdictionOfIssueCountryCode(i.getJurisdictionOfIssueCountryCode());
                return id;
            }).toList();
            clientDet.setIdentifications(identifications);
            List<com.example.JsonSchemaAndValidation.model.AuthorizedPersons> authorizedPersonsList = authPersonEntList.stream().map(a -> {
                com.example.JsonSchemaAndValidation.model.AuthorizedPersons auth = new com.example.JsonSchemaAndValidation.model.AuthorizedPersons();
                auth.setGivenName(a.getGivenName());
                auth.setSurName(a.getSurName());
                auth.setOtherNameInitial(a.getOtherNameInitial());
                return auth;
            }).toList();
            clientDet.setAuthorizedPersons(authorizedPersonsList);
            return clientDet;
        }).toList();
        msgDetails.setClientDetails(clientList);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        JsonNode node = mapper.valueToTree(msgDetails);
        return validateJson(node);

    }

    public List<String> validateJsonNew(JsonNode request) {
        List<String> allErrorMessages = null;
        try {
            JSONObject jsonSchema = new JSONObject("{\n" +
                    "    \"$schema\": \"https://json-schema.org/draft/2020-12/schema\",\n" +
                    "    \"$defs\": {\n" +
                    "        \"TypeCodeEnum\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"enum\": [\n" +
                    "                \"Incorporated\",\n" +
                    "                \"Registered\",\n" +
                    "                \"Incorporated and Registered\",\n" +
                    "                \"Unknown\"\n" +
                    "            ]\n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"type\": \"object\",\n" +
                    "    \"properties\": {\n" +
                    "        \"clientDetails\": {\n" +
                    "            \"type\": \"array\",\n" +
                    "            \"items\": {\n" +
                    "                \"type\": \"object\",\n" +
                    "                \"properties\": {\n" +
                    "                    \"address\": {\n" +
                    "                        \"type\": \"object\",\n" +
                    "                        \"properties\": {\n" +
                    "                            \"addrLine1\": {\n" +
                    "                                \"type\": \"string\",\n" +
                    "                                \"maxLength\": 100\n" +
                    "                            },\n" +
                    "                            \"addrLine2\": {\n" +
                    "                                \"type\": \"string\",\n" +
                    "                                \"maxLength\": 100\n" +
                    "                            },\n" +
                    "                            \"city\": {\n" +
                    "                                \"type\": \"string\",\n" +
                    "                                \"maxLength\": 100\n" +
                    "                            },\n" +
                    "                            \"countryCode\": {\n" +
                    "                                \"type\": \"string\",\n" +
                    "                                \"maxLength\": 2\n" +
                    "                            },\n" +
                    "                            \"stateCode\": {\n" +
                    "                                \"type\": \"string\",\n" +
                    "                                \"maxLength\": 2\n" +
                    "                            }\n" +
                    "                        }\n" +
                    "                    },\n" +
                    "                    \"authorizedPersons\": {\n" +
                    "                        \"type\": \"array\",\n" +
                    "                        \"items\": {\n" +
                    "                            \"type\": \"object\",\n" +
                    "                            \"properties\": {\n" +
                    "                                \"givenName\": {\n" +
                    "                                    \"type\": \"string\",\n" +
                    "                                    \"maxLength\": 100\n" +
                    "                                },\n" +
                    "                                \"otherNameInitial\": {\n" +
                    "                                    \"type\": \"string\",\n" +
                    "                                    \"maxLength\": 100\n" +
                    "                                },\n" +
                    "                                \"surName\": {\n" +
                    "                                    \"type\": \"string\",\n" +
                    "                                    \"maxLength\": 100\n" +
                    "                                }\n" +
                    "                            }\n" +
                    "                        }\n" +
                    "                    },\n" +
                    "                    \"clientNumber\": {\n" +
                    "                        \"type\": \"string\",\n" +
                    "                        \"maxLength\": 10\n" +
                    "                    },\n" +
                    "                    \"customerNumber\": {\n" +
                    "                        \"type\": \"string\",\n" +
                    "                        \"maxLength\": 20\n" +
                    "                    },\n" +
                    "                    \"identifications\": {\n" +
                    "                        \"type\": \"array\",\n" +
                    "                        \"items\": {\n" +
                    "                            \"type\": \"object\",\n" +
                    "                            \"properties\": {\n" +
                    "                                \"jurisdictionOfIssueCountryCode\": {\n" +
                    "                                    \"type\": \"string\",\n" +
                    "                                    \"maxLength\": 2\n" +
                    "                                },\n" +
                    "                                \"jurisdictionOfIssueProvinceStateCode\": {\n" +
                    "                                    \"type\": \"string\",\n" +
                    "                                    \"maxLength\": 2\n" +
                    "                                },\n" +
                    "                                \"number\": {\n" +
                    "                                    \"type\": \"string\",\n" +
                    "                                    \"maxLength\": 100\n" +
                    "                                },\n" +
                    "                                \"typeCode\": {\n" +
                    "                                    \"$ref\": \"#/$defs/TypeCodeEnum\"\n" +
                    "                                }\n" +
                    "                            },\n" +
                    "                            \"required\": [\n" +
                    "                                \"typeCode\"\n" +
                    "                            ]\n" +
                    "                        }\n" +
                    "                    },\n" +
                    "                    \"nameOfEntity\": {\n" +
                    "                        \"type\": \"string\",\n" +
                    "                        \"maxLength\": 100\n" +
                    "                    },\n" +
                    "                    \"natureOfPrincipalBusiness\": {\n" +
                    "                        \"type\": \"string\",\n" +
                    "                        \"maxLength\": 200\n" +
                    "                    },\n" +
                    "                    \"registrationIncorporationIndicator\": {\n" +
                    "                        \"type\": \"boolean\"\n" +
                    "                    },\n" +
                    "                    \"registrationIncorporations\": {\n" +
                    "                        \"type\": \"array\",\n" +
                    "                        \"items\": {\n" +
                    "                            \"type\": \"object\",\n" +
                    "                            \"properties\": {\n" +
                    "                                \"identifierTypeCode\": {\n" +
                    "                                    \"$ref\": \"#/$defs/TypeCodeEnum\"\n" +
                    "                                },\n" +
                    "                                \"jurisdictionOfIssueCountryCode\": {\n" +
                    "                                    \"type\": \"string\",\n" +
                    "                                    \"maxLength\": 2\n" +
                    "                                },\n" +
                    "                                \"jurisdictionOfIssueProvinceStateCode\": {\n" +
                    "                                    \"type\": \"string\",\n" +
                    "                                    \"maxLength\": 2\n" +
                    "                                },\n" +
                    "                                \"number\": {\n" +
                    "                                    \"type\": \"string\",\n" +
                    "                                    \"maxLength\": 100\n" +
                    "                                }\n" +
                    "                            },\n" +
                    "                            \"required\": [\n" +
                    "                                \"identifierTypeCode\"\n" +
                    "                            ]\n" +
                    "                        }\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                \"required\": [\n" +
                    "                    \"address\",\n" +
                    "                    \"authorizedPersons\",\n" +
                    "                    \"clientNumber\",\n" +
                    "                    \"customerNumber\",\n" +
                    "                    \"identifications\",\n" +
                    "                    \"registrationIncorporations\"\n" +
                    "                ]\n" +
                    "            }\n" +
                    "        },\n" +
                    "        \"msgDateTimestamp\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"format\": \"date-time\"\n" +
                    "        },\n" +
                    "        \"msgID\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"minLength\": 1\n" +
                    "        },\n" +
                    "        \"sourceSystem\": {\n" +
                    "            \"type\": \"string\",\n" +
                    "            \"enum\": [\n" +
                    "                \"FLEXCUBE\",\n" +
                    "                \"LIQ\"\n" +
                    "            ]\n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"required\": [\n" +
                    "        \"msgDateTimestamp\",\n" +
                    "        \"msgID\"\n" +
                    "    ]\n" +
                    "}");
            SchemaLoader loader = SchemaLoader.builder()
                    .schemaJson(jsonSchema)
                    .draftV7Support() // or draftV7Support()
                    .build();
            Schema schema = loader.load().build();
            System.out.println(schema.toString());
            schema.validate(request);
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
            ve.getCausingExceptions().stream()
                    .map(ValidationException::getMessage)
                    .forEach(System.out::println);
        }
        return allErrorMessages;
    }
}
