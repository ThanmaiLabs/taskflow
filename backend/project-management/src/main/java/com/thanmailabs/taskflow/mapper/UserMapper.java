package com.thanmailabs.taskflow.mapper;

import com.thanmailabs.taskflow.dto.request.UserRegistrationRequest;
import com.thanmailabs.taskflow.dto.response.UserRegistrationResponse;
import com.thanmailabs.taskflow.entity.Address;
import com.thanmailabs.taskflow.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRegistrationRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        Address address = new Address();
        address.setStreet(request.getAddress().getStreet());
        address.setCity(request.getAddress().getCity());
        address.setCountry(request.getAddress().getCountry());
        address.setLaneNumber(request.getAddress().getLaneNumber());
        address.setState(request.getAddress().getState());

        user.setAddress(address);
        return user;
    }

    public UserRegistrationResponse toDTO(User user) {
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setId(user.getId());
        return response;
    }
}
