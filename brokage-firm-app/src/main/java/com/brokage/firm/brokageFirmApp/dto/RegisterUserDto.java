package com.brokage.firm.brokageFirmApp.dto;

import com.brokage.firm.brokageFirmApp.entity.UserRole;

public class RegisterUserDto {
    
    private String username;

    private String password;

    private UserRole role;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }
}
