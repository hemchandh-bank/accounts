package com.example.accounts.service;

import com.example.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * Creates a new account for the given customer.
     *
     * @param customerDto the customer data transfer object containing the details of the customer.
     */
    void createAccount(CustomerDto customerDto);

    /**
     * Fetches the account details for the given mobile number.
     *
     * @param mobileNumber the mobile number of the customer.
     * @return a {@link CustomerDto} containing the account details of the customer.
     * @throws ResourceNotFoundException if the account does not exist.
     */
    CustomerDto fetchAccounts(String mobileNumber);
}
