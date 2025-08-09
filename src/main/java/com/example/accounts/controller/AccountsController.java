package com.example.accounts.controller;

import com.example.accounts.constants.AccountsConstants;
import com.example.accounts.dto.CustomerDto;
import com.example.accounts.dto.ResponseDto;
import com.example.accounts.service.IAccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated //Spring-boot framework know to perform validations on all defined in this controller
public class AccountsController {

    IAccountsService iAccountsService;

    /**
     * Create a new account for the given customer.
     *
     * @param customerDto the customer data transfer object containing the details of the customer.
     * @return a response entity containing the status of the request.
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    /**
     * Fetches account details for a customer based on the provided mobile number.
     *
     * @param mobileNumber the mobile number of the customer whose account details are to be fetched.
     * @return a response entity containing the customer data transfer object with account details.
     */
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> getAccountsDetails(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
            String mobileNumber) {
        CustomerDto customerDto = iAccountsService.fetchAccounts(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    /**
     * Updates the customer details.
     *
     * @param customerDto the customer data transfer object containing updated details.
     * @return a response entity containing the status of the update operation.
     */
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean updateStatus = iAccountsService.updateCustomerDetails(customerDto);
        if (updateStatus) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));

        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));

        }
    }

    /**
     * Deletes the customer and their associated accounts based on the provided mobile number.
     *
     * @param mobileNumber the mobile number of the customer to be deleted.
     * @return a response entity containing the status of the delete operation.
     */
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCustomer(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
            String mobileNumber) {
        boolean deleteStatus = iAccountsService.deleteCustomer(mobileNumber);
        if (deleteStatus)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        else
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
    }

    @GetMapping("/msg")
    public String getMsg() {
        return "Hello world";
    }
}
