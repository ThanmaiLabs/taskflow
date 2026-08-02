package com.thanmailabs.taskflow.dto.response;

import lombok.Data;

@Data
public class UserSummary {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
