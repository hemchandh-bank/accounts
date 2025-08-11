package com.example.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Schema to hold Customer and Account information")
public class CustomerDto {

    @NotEmpty(message = "Name should not be null or empty")
    @Size(min = 3, max = 25, message = "The length of name should between 3 to 25")
    @Schema(description = "Name of the customer", example = "Hemchandh")
    private String name;

    @NotEmpty(message = "Email should not be null or empty")
    @Email(message = "Enter valid email address")
    @Schema(description = "Email address of the customer", example = "mail@gmail.com")
    private String email;

    @NotEmpty(message = "Mobile number should be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
    @Schema(description = "Mobile Number of the customer", example = "9990009990")
    private String mobileNumber;

    @Schema(description = "Account details of the Customer")
    private AccountsDto accountsDto;
}
