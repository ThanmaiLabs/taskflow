package com.thanmailabs.taskflow.service;

import com.thanmailabs.taskflow.dto.request.LoginRequest;
import com.thanmailabs.taskflow.dto.response.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest request);
}
