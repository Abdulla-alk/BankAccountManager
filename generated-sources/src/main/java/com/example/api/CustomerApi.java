/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.example.api;

import com.example.model.Customer;
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
@Api(value = "Customer", description = "the Customer API")
public interface CustomerApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /customers : Create a new customer
     * Create a new customer
     *
     * @param customer  (required)
     * @return Customer created successfully (status code 201)
     *         or Error response (status code 400)
     *         or Error response (status code 500)
     */

    @ApiOperation(value = "Create a new customer", nickname = "createCustomer", notes = "Create a new customer", response = Customer.class, tags={ "Customer", })
    @ApiResponses(value = { 

        @ApiResponse(code = 201, message = "Customer created successfully", response = Customer.class),

        @ApiResponse(code = 400, message = "Error response", response = InlineResponse500.class),

        @ApiResponse(code = 500, message = "Error response", response = InlineResponse500.class) })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/customers",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Customer> createCustomer(

@ApiParam(value = "", required = true )   @Valid @RequestBody Customer customer) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"address\" : \"address\", \"statusId\" : 6, \"phone\" : \"phone\", \"dateOfBirth\" : \"2000-01-23\", \"id\" : 0, \"email\" : \"email\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /customers/{id} : Delete a customer by ID
     * Delete a customer by ID
     *
     * @param id  (required)
     * @return Customer deleted successfully (status code 204)
     *         or Error response (status code 404)
     *         or Error response (status code 500)
     */

    @ApiOperation(value = "Delete a customer by ID", nickname = "deleteCustomer", notes = "Delete a customer by ID", tags={ "Customer", })
    @ApiResponses(value = { 

        @ApiResponse(code = 204, message = "Customer deleted successfully"),

        @ApiResponse(code = 404, message = "Error response", response = InlineResponse500.class),

        @ApiResponse(code = 500, message = "Error response", response = InlineResponse500.class) })
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/customers/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<Void> deleteCustomer(@ApiParam(value = "", required = true) @PathVariable("id") Long id

) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /customers : Retrieve all customers
     * Retrieve all customers
     *
     * @return A list of customers (status code 200)
     *         or Error response (status code 500)
     */

    @ApiOperation(value = "Retrieve all customers", nickname = "getAllCustomers", notes = "Retrieve all customers", response = Customer.class, responseContainer = "List", tags={ "Customer", })
    @ApiResponses(value = { 

        @ApiResponse(code = 200, message = "A list of customers", response = Customer.class, responseContainer = "List"),

        @ApiResponse(code = 500, message = "Error response", response = InlineResponse500.class) })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/customers",
        produces = { "application/json" }
    )
    default ResponseEntity<List<Customer>> getAllCustomers() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"address\" : \"address\", \"statusId\" : 6, \"phone\" : \"phone\", \"dateOfBirth\" : \"2000-01-23\", \"id\" : 0, \"email\" : \"email\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /customers/{id} : Retrieve a customer by ID
     * Retrieve a customer by ID
     *
     * @param id  (required)
     * @return Customer details (status code 200)
     *         or Error response (status code 404)
     *         or Error response (status code 500)
     */

    @ApiOperation(value = "Retrieve a customer by ID", nickname = "getCustomerById", notes = "Retrieve a customer by ID", response = Customer.class, tags={ "Customer", })
    @ApiResponses(value = { 

        @ApiResponse(code = 200, message = "Customer details", response = Customer.class),

        @ApiResponse(code = 404, message = "Error response", response = InlineResponse500.class),

        @ApiResponse(code = 500, message = "Error response", response = InlineResponse500.class) })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/customers/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<Customer> getCustomerById(@ApiParam(value = "", required = true) @PathVariable("id") Long id

) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"address\" : \"address\", \"statusId\" : 6, \"phone\" : \"phone\", \"dateOfBirth\" : \"2000-01-23\", \"id\" : 0, \"email\" : \"email\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /customers/{id} : Update customer information
     * Update customer information by ID
     *
     * @param id  (required)
     * @param customer  (required)
     * @return Customer updated successfully (status code 200)
     *         or Error response (status code 400)
     *         or Error response (status code 404)
     *         or Error response (status code 500)
     */

    @ApiOperation(value = "Update customer information", nickname = "updateCustomer", notes = "Update customer information by ID", response = Customer.class, tags={ "Customer", })
    @ApiResponses(value = { 

        @ApiResponse(code = 200, message = "Customer updated successfully", response = Customer.class),

        @ApiResponse(code = 400, message = "Error response", response = InlineResponse500.class),

        @ApiResponse(code = 404, message = "Error response", response = InlineResponse500.class),

        @ApiResponse(code = 500, message = "Error response", response = InlineResponse500.class) })
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/customers/{id}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Customer> updateCustomer(@ApiParam(value = "", required = true) @PathVariable("id") Long id

,

@ApiParam(value = "", required = true )   @Valid @RequestBody Customer customer) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"address\" : \"address\", \"statusId\" : 6, \"phone\" : \"phone\", \"dateOfBirth\" : \"2000-01-23\", \"id\" : 0, \"email\" : \"email\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}