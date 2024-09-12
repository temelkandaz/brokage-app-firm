package com.brokage.firm.brokageFirmApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brokage.firm.brokageFirmApp.dto.LoginUserDto;
import com.brokage.firm.brokageFirmApp.dto.RegisterUserDto;
import com.brokage.firm.brokageFirmApp.entity.User;
import com.brokage.firm.brokageFirmApp.response.LoginResponse;
import com.brokage.firm.brokageFirmApp.response.RegisterResponse;
import com.brokage.firm.brokageFirmApp.service.AuthenticationService;
import com.brokage.firm.brokageFirmApp.service.JwtService;
import com.brokage.firm.brokageFirmApp.service.RegistrationService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = registrationService.register(registerUserDto);

        RegisterResponse registerResponse = (new RegisterResponse()).toRegisterResponse(registeredUser);

        return ResponseEntity.ok(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
