package com.thanmailabs.taskflow.service.impl;

import com.thanmailabs.taskflow.dto.request.LoginRequest;
import com.thanmailabs.taskflow.dto.response.LoginResponse;
import com.thanmailabs.taskflow.entity.User;
import com.thanmailabs.taskflow.exception.InvalidCredentialsException;
import com.thanmailabs.taskflow.mapper.UserMapper;
import com.thanmailabs.taskflow.repository.UserRepository;
import com.thanmailabs.taskflow.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private static final String JWT_PLACEHOLDER =
            "JWT_TOKEN_PLACEHOLDER";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    @Override
    public LoginResponse login(LoginRequest request) {
        String normalizedEmail = request.getEmail().trim().toLowerCase();
        User user = userRepository.findByEmail(normalizedEmail).orElseThrow(InvalidCredentialsException::new);

        boolean isValidPassword = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!isValidPassword) {
            throw new InvalidCredentialsException();
        }

        LoginResponse response = userMapper.toLoginDTO(user);
        response.setAccessToken(JWT_PLACEHOLDER);
        return response;
    }
}
