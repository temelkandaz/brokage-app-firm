package com.brokage.firm.brokageFirmApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brokage.firm.brokageFirmApp.dto.DepositMoneyDto;
import com.brokage.firm.brokageFirmApp.entity.Customer;
import com.brokage.firm.brokageFirmApp.service.CustomerService;

@RequestMapping("/customer")
@RestController
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    @PutMapping("/{customerId}/deposit")
    public ResponseEntity<Boolean> depositMoney (
        @PathVariable("customerId") Long customerId,
        @RequestBody DepositMoneyDto depositMoneyDto
    ) {
        Customer customer = customerService.getCustomerById(customerId);

        if (customer == null) {
            return ResponseEntity.ok(false);
        }

        customerService.depositMoneyForCustomer(customerId, depositMoneyDto.getAmount());

        return ResponseEntity.ok(true);
    }
}
