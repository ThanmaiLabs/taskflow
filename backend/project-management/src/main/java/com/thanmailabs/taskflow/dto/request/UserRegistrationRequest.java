package com.thanmailabs.taskflow.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationRequest {
    @NotBlank
    @Size(max = 50,
          message = "First name cannot exceed 50 characters")
    private String firstName;
    @NotBlank
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String lastName;
    @NotBlank
    @Email
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;
    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    @NotNull
    @Valid
    private AddressRequest address;
}
