package com.thanmailabs.taskflow.dto.response;

import lombok.Data;

@Data
public class UserRegistrationResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
