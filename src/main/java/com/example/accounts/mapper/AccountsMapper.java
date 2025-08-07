package com.example.accounts.mapper;

import com.example.accounts.dto.AccountsDto;
import com.example.accounts.entity.Accounts;

public class AccountsMapper {

    /**
     * Maps the properties from an Accounts entity to an AccountsDto object.
     *
     * @param accounts the Accounts entity containing account details.
     * @param accountsDto the data transfer object to be updated with account details.
     * @return the updated AccountsDto object containing the account details.
     */
    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    /**
     * Maps the properties from an AccountsDto object to an Accounts entity.
     *
     * @param accountsDto the data transfer object containing account details.
     * @param accounts the Accounts entity to be updated with details from the DTO.
     * @return the updated Accounts entity with details from the DTO.
     */
    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
