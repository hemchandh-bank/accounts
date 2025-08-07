package com.example.accounts.mapper;

import com.example.accounts.dto.CustomerDto;
import com.example.accounts.entity.Customer;

public class CustomerMapper {

    /**
     * Maps a {@link Customer} to a {@link CustomerDto}.
     *
     * @param customer the customer object whose fields are to be mapped.
     * @param customerDto the customer data transfer object whose fields are to be populated.
     * @return the customer data transfer object with the populated fields.
     */
    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    /**
     * Maps a {@link CustomerDto} to a {@link Customer}.
     *
     * @param customerDto the customer data transfer object containing the details of the customer.
     * @param customer     the customer object whose fields are to be populated.
     * @return the customer object with the populated fields.
     */
    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
