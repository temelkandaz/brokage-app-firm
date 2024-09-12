package com.brokage.firm.brokageFirmApp.response;

import java.sql.Timestamp;

import com.brokage.firm.brokageFirmApp.entity.User;
import com.brokage.firm.brokageFirmApp.entity.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
    
    private String username;

    private UserRole userRole;

    private Timestamp createdAt;

    public RegisterResponse toRegisterResponse(User user) {
        RegisterResponse registerResponse = new RegisterResponse();

        registerResponse.setUsername(user.getUsername());
        registerResponse.setUserRole(user.getRole());
        registerResponse.setCreatedAt(user.getCreatedAt());

        return registerResponse;
    }
}
