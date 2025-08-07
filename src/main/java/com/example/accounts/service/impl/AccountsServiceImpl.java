package com.example.accounts.service.impl;

import com.example.accounts.constants.AccountsConstants;
import com.example.accounts.dto.AccountsDto;
import com.example.accounts.dto.CustomerDto;
import com.example.accounts.entity.Accounts;
import com.example.accounts.entity.Customer;
import com.example.accounts.exception.CustomerAlreadyExistsException;
import com.example.accounts.exception.ResourceNotFoundException;
import com.example.accounts.mapper.AccountsMapper;
import com.example.accounts.mapper.CustomerMapper;
import com.example.accounts.repository.AccountsRepository;
import com.example.accounts.repository.CustomerRepository;
import com.example.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * Creates a new account for the given customer.
     *
     * @param customerDto the customer data transfer object containing the details of the customer.
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        checkCustomerAlreadyExists(customerDto);
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Admin");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * Fetches the account details for a customer based on the provided mobile number.
     *
     * @param mobileNumber the mobile number of the customer whose account details are to be fetched.
     * @return a {@link CustomerDto} containing the customer's account details.
     * @throws ResourceNotFoundException if no customer or account is found for the provided mobile number.
     */
    @Override
    public CustomerDto fetchAccounts(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "Mobile number", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Accounts", "customer id", customer.getCustomerId().toString())
        );
        CustomerDto customerDtoResponse = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDtoResponse.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        return customerDtoResponse;
    }

    /**
     * Check if a customer does not exist by mobile number
     *
     * @param customerDto the customer data transfer object containing the details of the customer.
     * @throws CustomerAlreadyExistsException if the customer already exists.
     */
    private void checkCustomerAlreadyExists(CustomerDto customerDto) {
        if (customerRepository.findByMobileNumber(customerDto.getMobileNumber()).isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with this mobile number already exists: " + customerDto.getMobileNumber());
        }
    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1_00_00_00_000L + new Random().nextInt(90_00_00_000);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Admin");
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }
}
