package com.brokage.firm.brokageFirmApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brokage.firm.brokageFirmApp.dto.DepositMoneyDto;
import com.brokage.firm.brokageFirmApp.dto.WithdrawMoneyDto;
import com.brokage.firm.brokageFirmApp.entity.Customer;
import com.brokage.firm.brokageFirmApp.service.CustomerService;

@RequestMapping("/customer")
@RestController
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    @PutMapping("/{customerId}/deposit")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
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

    @PutMapping("/{customerId}/withdraw")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<Boolean> withdrawMoney (
        @PathVariable("customerId") Long customerId,
        @RequestBody WithdrawMoneyDto withdrawMoneyDto
    ) {
        Customer customer = customerService.getCustomerById(customerId);

        if (customer == null) {
            return ResponseEntity.ok(false);
        }

        if (customer.getBalance() < withdrawMoneyDto.getAmount()) {
            return ResponseEntity.ok(false);
        }

        customerService.withdrawMoneyForCustomer(
            customerId, withdrawMoneyDto.getAmount(), withdrawMoneyDto.getIBAN()
        );

        return ResponseEntity.ok(true);
    }
}
