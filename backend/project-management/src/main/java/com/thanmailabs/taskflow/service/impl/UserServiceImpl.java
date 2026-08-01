package com.thanmailabs.taskflow.service.impl;

import com.thanmailabs.taskflow.dto.request.UserRegistrationRequest;
import com.thanmailabs.taskflow.dto.response.UserRegistrationResponse;
import com.thanmailabs.taskflow.entity.User;
import com.thanmailabs.taskflow.exception.UserAlreadyExistsException;
import com.thanmailabs.taskflow.mapper.UserMapper;
import com.thanmailabs.taskflow.repository.UserRepository;
import com.thanmailabs.taskflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    private final PasswordEncoder passwordEncoder;
    @Override
    public UserRegistrationResponse register(UserRegistrationRequest request) {
        String normalizedEmail = request.getEmail().trim().toLowerCase();
        Optional<User> existingUser = userRepository.findByEmail(normalizedEmail);
        if(existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = mapper.toEntity(request);
        user.setEmail(normalizedEmail);
        user.setPassword(encodedPassword);

        User savedUser = userRepository.save(user);
        return mapper.toDTO(savedUser);
    }
}
