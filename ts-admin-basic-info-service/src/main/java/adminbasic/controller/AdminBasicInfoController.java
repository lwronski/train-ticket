package adminbasic.controller;

import adminbasic.entity.*;
import adminbasic.service.AdminBasicInfoService;
import edu.fudan.common.util.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/adminbasicservice")
public class AdminBasicInfoController {

    @Autowired
    AdminBasicInfoService adminBasicInfoService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminBasicInfoController.class);

    @GetMapping(path = "/welcome")
    public String home(@RequestHeader HttpHeaders headers) {
        return "Welcome to [ AdminBasicInfo Service ] !";
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/adminbasic/contacts")
    @ApiImplicitParam(name = "headers", paramType = "header", required = true)
    @ApiResponses({
            @ApiResponse(code=1, message = "success", response= Contacts.class,responseContainer = "ArrayList"),
            @ApiResponse(code = 0, message = "No content")
    })
    public HttpEntity getAllContacts(@RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[Admin Basic Info Service][Find All Contacts by admin ");
        return ok(adminBasicInfoService.getAllContacts(headers));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/adminbasic/contacts/{contactsId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contactsId", dataType = "String", paramType = "path", required = true,defaultValue = "75ce3fe9-5cdb-49e9-bd27-6df080a2f38b"),
            @ApiImplicitParam(name = "headers", paramType = "header", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "Delete success", response = UUID.class),
            @ApiResponse(code = 0, message = "Delete failed", response = UUID.class)
    })
    public HttpEntity deleteContacts(@PathVariable String contactsId, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[Admin Basic Info Service][Delete Contacts by admin ");
        return ok(adminBasicInfoService.deleteContact(contactsId, headers));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/adminbasic/contacts")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mci",value = "Contacts",dataType = "Contacts", paramType = "body",required = true),
            @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "Contacts not found"),
            @ApiResponse(code = 1, message = "Modify success", response = Contacts.class)
    })
    public HttpEntity modifyContacts(@RequestBody Contacts mci, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[Admin Basic Info Service][Modify Contacts by admin: ");
        return ok(adminBasicInfoService.modifyContact(mci, headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/adminbasic/contacts")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "Contacts",dataType = "Contacts", paramType = "body",required = true),
            @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "Already Exists", response = Contacts.class),
            @ApiResponse(code = 1, message = "Create Success")
    })
    public HttpEntity addContacts(@RequestBody Contacts c, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[Admin Basic Info Service][Modify Contacts by admin  ");
        return ok(adminBasicInfoService.addContact(c, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/adminbasic/stations")
    @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    @ApiResponses({
            @ApiResponse(code = 1, message = "Find all content", response = Station.class,responseContainer = "List"),
            @ApiResponse(code = 0, message = "No content")
    })
    public HttpEntity getAllStations(@RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[Admin Basic Info Service][Find All Station by admin  ");
        return ok(adminBasicInfoService.getAllStations(headers));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/adminbasic/stations")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "s", value = "Station",dataType = "Station", paramType = "body",required = true),
            @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "Delete success", response = Station.class),
            @ApiResponse(code = 0, message = "Station not exist")
    })
    public HttpEntity deleteStation(@RequestBody Station s, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[Admin Basic Info Service][Delete Station by admin ");
        return ok(adminBasicInfoService.deleteStation(s, headers));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/adminbasic/stations")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "s", value = "Station",dataType = "Station", paramType = "body",required = true),
            @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "Station not exist"),
            @ApiResponse(code = 1, message = "Update success", response = Station.class)
    })
    public HttpEntity modifyStation(@RequestBody Station s, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[Admin Basic Info Service][Modify Station by admin ");
        return ok(adminBasicInfoService.modifyStation(s, headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/adminbasic/stations")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "s", value = "Station",dataType = "Station", paramType = "body",required = true),
            @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "Create success", response = Station.class),
            @ApiResponse(code = 0, message = "Already exists", response = Station.class)
    })
    public HttpEntity addStation(@RequestBody Station s, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[Admin Basic Info Service][Modify Station by admin");
        return ok(adminBasicInfoService.addStation(s, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/adminbasic/trains")
    @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    @ApiResponses({
            @ApiResponse(code = 1, message = "success", response = TrainType.class,responseContainer = "List") ,
            @ApiResponse(code = 0, message = "no content", response = TrainType.class,responseContainer = "List")
    })
    public HttpEntity getAllTrains(@RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[Admin Basic Info Service][Find All Train by admin: ");
        return ok(adminBasicInfoService.getAllTrains(headers));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/adminbasic/trains/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id",dataType = "String", paramType = "path",required = true,defaultValue = "GaoTieOne"),
            @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "delete success", response =boolean.class),
            @ApiResponse(code = 0, message = "there is no train according to id")
    })
    public HttpEntity deleteTrain(@PathVariable String id, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[Admin Basic Info Service][Delete Train by admin");
        return ok(adminBasicInfoService.deleteTrain(id, headers));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/adminbasic/trains")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "t", value = "TrainType",dataType = "TrainType", paramType = "body",required = true),
            @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "update success",response = boolean.class),
            @ApiResponse(code = 0, message = "there is no trainType with the trainType id",response = boolean.class)
    })
    public HttpEntity modifyTrain(@RequestBody TrainType t, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[Admin Basic Info Service][Modify Train by admin  ");
        return ok(adminBasicInfoService.modifyTrain(t, headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/adminbasic/trains")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "t", value = "TrainType",dataType = "TrainType", paramType = "body",required = true),
            @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "create success"),
            @ApiResponse(code = 0, message = "train type already exist", response = TrainType.class)
    })
    public HttpEntity addTrain(@RequestBody TrainType t, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[Admin Basic Info Service][Modify Train by admin ");
        return ok(adminBasicInfoService.addTrain(t, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/adminbasic/configs")
    @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    @ApiResponses({
            @ApiResponse(code = 1, message = "Find all  config success",response = Config.class,responseContainer = "List"),
            @ApiResponse(code = 0, message = "No content")
    })
    public HttpEntity getAllConfigs(@RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[Admin Basic Info Service][Find All Config by admin  ");
        return ok(adminBasicInfoService.getAllConfigs(headers));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/adminbasic/configs/{name}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name",dataType = "String", paramType = "path",required = true,defaultValue = "DirectTicketAllocationProportion"),
            @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "Config doesn't exist."),
            @ApiResponse(code = 1, message = "Delete success", response = Config.class)
    })
    public HttpEntity deleteConfig(@PathVariable String name, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[Admin Basic Info Service][Delete Config by admin ");
        return ok(adminBasicInfoService.deleteConfig(name, headers));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/adminbasic/configs")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "Config",dataType = "Config", paramType = "body",required = true),
            @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "Config doesn't exist."),
            @ApiResponse(code = 1, message = "Update success", response = Config.class)
    })
    public HttpEntity modifyConfig(@RequestBody Config c, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[Admin Basic Info Service][Modify Config by admin ");
        return ok(adminBasicInfoService.modifyConfig(c, headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/adminbasic/configs")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "Config",dataType = "Config", paramType = "body",required = true),
            @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "Config already exists."),
            @ApiResponse(code = 1, message = "Create success", response = Config.class)
    })
    public HttpEntity addConfig(@RequestBody Config c, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[Admin Basic Info Service][Modify Config by admin  ");
        return ok(adminBasicInfoService.addConfig(c, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/adminbasic/prices")
    @ApiResponses({
            @ApiResponse(code = 1, message = "Success",response = PriceConfig.class,responseContainer = "List"),
            @ApiResponse(code = 0, message = "No price config")
    })
    public HttpEntity getAllPrices(@RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[Admin Basic Info Service][Find All Price by admin ");
        return ok(adminBasicInfoService.getAllPrices(headers));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/adminbasic/prices")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pi", value = "PriceInfo",dataType = "PriceInfo", paramType = "body",required = true),
            @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "No that config"),
            @ApiResponse(code = 1, message = "Delete success",response = PriceConfig.class)
    })
    public HttpEntity deletePrice(@RequestBody PriceInfo pi, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[Admin Basic Info Service][Delete Price by admin  ");
        return ok(adminBasicInfoService.deletePrice(pi, headers));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/adminbasic/prices")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pi", value = "PriceInfo",dataType = "PriceInfo", paramType = "body",required = true),
            @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "No that config"),
            @ApiResponse(code = 1, message = "Update success",response = PriceConfig.class)
    })
    public HttpEntity modifyPrice(@RequestBody PriceInfo pi, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[Admin Basic Info Service][Modify Price by admin  ");
        return ok(adminBasicInfoService.modifyPrice(pi, headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/adminbasic/prices")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pi", value = "PriceInfo",dataType = "PriceInfo", paramType = "body",required = true),
            @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    })
    @ApiResponse(code = 1, message = "Create success",response = PriceConfig.class)
    public HttpEntity addPrice(@RequestBody PriceInfo pi, @RequestHeader HttpHeaders headers) {
        AdminBasicInfoController.LOGGER.info("[Admin Basic Info Service][Add Price by admin");
        return ok(adminBasicInfoService.addPrice(pi, headers));
    }

}
