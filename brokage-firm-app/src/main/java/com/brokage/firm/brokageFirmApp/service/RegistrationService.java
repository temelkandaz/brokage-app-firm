package com.brokage.firm.brokageFirmApp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.brokage.firm.brokageFirmApp.dto.RegisterUserDto;
import com.brokage.firm.brokageFirmApp.entity.User;
import com.brokage.firm.brokageFirmApp.repository.UserRepository;

@Service
public class RegistrationService {
    
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public RegistrationService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterUserDto registerUserInput) {
        String encodedPassword = passwordEncoder.encode(registerUserInput.getPassword());

        User user = new User();

        user.setUsername(registerUserInput.getUsername());
        user.setPassword(encodedPassword);
        user.setRole(registerUserInput.getRole());
        
        return userRepository.save(user);
    }
}
