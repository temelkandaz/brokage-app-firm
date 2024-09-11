package com.brokage.firm.brokageFirmApp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brokage.firm.brokageFirmApp.dto.LoginUserDto;
import com.brokage.firm.brokageFirmApp.dto.RegisterUserDto;
import com.brokage.firm.brokageFirmApp.entity.User;
import com.brokage.firm.brokageFirmApp.response.LoginResponse;
import com.brokage.firm.brokageFirmApp.service.AuthenticationService;
import com.brokage.firm.brokageFirmApp.service.JwtService;
import com.brokage.firm.brokageFirmApp.service.RegistrationService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    private final RegistrationService registrationService;

    public AuthenticationController(
        JwtService jwtService, 
        AuthenticationService authenticationService,
        RegistrationService registrationService
    ) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = registrationService.register(registerUserDto);

        return ResponseEntity.ok(registeredUser);
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
