package com.example.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Accounts", description = "Schema to hold Account information")
public class AccountsDto {

    @NotEmpty(message = "Account number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})")
    @Schema(description = "Account Number of H-Bank account", example = "1234567890")
    private Long accountNumber;

    @NotEmpty(message = "AccountType cannot be null or empty")
    @Schema(description = "Account type of H-Bank account", example = "Savings")
    private String accountType;

    @NotEmpty(message = "Branch address cannot be null or empty")
    @Schema(description = "H-Bank branch address", example = "123-NewYork")
    private String branchAddress;
}
