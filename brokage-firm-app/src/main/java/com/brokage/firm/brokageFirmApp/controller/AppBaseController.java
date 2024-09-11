package com.brokage.firm.brokageFirmApp.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class AppBaseController {
    
    @GetMapping("/")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Service is up!");
    }
}
