package com.thanmailabs.taskflow.service;

import com.thanmailabs.taskflow.dto.request.UserRegistrationRequest;
import com.thanmailabs.taskflow.dto.response.UserRegistrationResponse;

public interface UserService {

    UserRegistrationResponse register(UserRegistrationRequest request);
}
