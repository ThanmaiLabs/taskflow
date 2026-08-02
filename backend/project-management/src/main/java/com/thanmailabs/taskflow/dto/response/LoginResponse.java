package com.thanmailabs.taskflow.dto.response;

import lombok.Data;

@Data
public class LoginResponse {
    private UserSummary user;
    private String accessToken;
}
