/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.example.api;

import com.example.model.AccountType;
import com.example.model.InlineResponse500;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-04T14:46:36.948950+03:00[Asia/Bahrain]")
@Validated
@Api(value = "Type", description = "the Type API")
public interface TypeApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /accountTypes/{id} : Retrieve an account type by ID
     * Retrieve an account type by ID
     *
     * @param id  (required)
     * @return Account type details (status code 200)
     *         or Error response (status code 404)
     *         or Error response (status code 500)
     */

    @ApiOperation(value = "Retrieve an account type by ID", nickname = "getAccountTypeById", notes = "Retrieve an account type by ID", response = AccountType.class, tags={ "Type", })
    @ApiResponses(value = { 

        @ApiResponse(code = 200, message = "Account type details", response = AccountType.class),

        @ApiResponse(code = 404, message = "Error response", response = InlineResponse500.class),

        @ApiResponse(code = 500, message = "Error response", response = InlineResponse500.class) })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/accountTypes/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<AccountType> getAccountTypeById(@ApiParam(value = "", required = true) @PathVariable("id") Long id

) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"typeName\" : \"typeName\", \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /accountTypes : Retrieve all account types
     * Retrieve all account types
     *
     * @return A list of account types (status code 200)
     *         or Error response (status code 500)
     */

    @ApiOperation(value = "Retrieve all account types", nickname = "getAllAccountTypes", notes = "Retrieve all account types", response = AccountType.class, responseContainer = "List", tags={ "Type", })
    @ApiResponses(value = { 

        @ApiResponse(code = 200, message = "A list of account types", response = AccountType.class, responseContainer = "List"),

        @ApiResponse(code = 500, message = "Error response", response = InlineResponse500.class) })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/accountTypes",
        produces = { "application/json" }
    )
    default ResponseEntity<List<AccountType>> getAllAccountTypes() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"typeName\" : \"typeName\", \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
