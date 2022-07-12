package consign.controller;

import consign.entity.Consign;
import consign.entity.ConsignRecord;
import consign.service.ConsignService;
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
import springfox.documentation.annotations.ApiIgnore;

import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/consignservice")
public class ConsignController {

    @Autowired
    ConsignService service;

    private static final Logger logger = LoggerFactory.getLogger(ConsignController.class);

    @GetMapping(path = "/welcome")
    public String home(@RequestHeader HttpHeaders headers) {
        return "Welcome to [ Consign Service ] !";
    }

    @ApiIgnore
    @PostMapping(value = "/consigns")
    public HttpEntity insertConsign(@RequestBody Consign request,
                                    @RequestHeader HttpHeaders headers) {
        logger.info("Insert consign record, id:{}", request.getId());
        return ok(service.insertConsignRecord(request, headers));
    }

    @PutMapping(value = "/consigns")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", value = "Consign",dataType = "Consign", paramType = "body",required = true),
            @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "Update consign success",response = ConsignRecord.class),
            @ApiResponse(code = 1, message = "You have consigned successfully! The price is ?",response = ConsignRecord.class)
    })
    public HttpEntity updateConsign(@RequestBody Consign request, @RequestHeader HttpHeaders headers) {
        logger.info("Update consign record, id: {}", request.getId());
        return ok(service.updateConsignRecord(request, headers));
    }

    @GetMapping(value = "/consigns/account/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id",dataType = "String", paramType = "path",required = true,defaultValue = "4d2a46c7-71cb-4cf1-b5bb-b68406d9da6f"),
            @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "Find consign by account id success",response = ConsignRecord.class,responseContainer = "List"),
            @ApiResponse(code = 0, message = "No Content according to accountId")
    })
    public HttpEntity findByAccountId(@PathVariable String id, @RequestHeader HttpHeaders headers) {
        logger.info("Find consign by account id: {}", id);
        UUID newid = UUID.fromString(id);
        return ok(service.queryByAccountId(newid, headers));
    }

    @GetMapping(value = "/consigns/order/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id",dataType = "String", paramType = "path",required = true,defaultValue = "fa9bd99c-2f28-45a9-9d87-7630caaccc6a"),
            @ApiImplicitParam(name = "headers",  paramType = "header",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "Find consign by order id success",response = ConsignRecord.class),
            @ApiResponse(code = 0, message = "No Content according to order id")
    })
    public HttpEntity findByOrderId(@PathVariable String id, @RequestHeader HttpHeaders headers) {
        logger.info("Find consign by order id: {}", id);
        UUID newid = UUID.fromString(id);
        return ok(service.queryByOrderId(newid, headers));
    }

    @ApiIgnore
    @GetMapping(value = "/consigns/{consignee}")
    public HttpEntity findByConsignee(@PathVariable String consignee, @RequestHeader HttpHeaders headers) {
        logger.info("Find consign by consignee: {}", consignee);
        return ok(service.queryByConsignee(consignee, headers));
    }

}
