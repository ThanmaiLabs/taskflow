package com.thanmailabs.taskflow.service;

import com.thanmailabs.taskflow.entity.User;

public interface JwtService {
    String generateAccessToken(User user);
    String getUserNameFromToken(String token);
    Long getUserIdFromToken(String token);
    boolean isValidAccessToken(String token);
}
