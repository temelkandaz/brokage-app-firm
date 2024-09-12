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
import com.brokage.firm.brokageFirmApp.exception.CustomerNotFoundException;
import com.brokage.firm.brokageFirmApp.exception.NotSufficientBalanceToWithdraw;
import com.brokage.firm.brokageFirmApp.service.CustomerService;
import com.brokage.firm.brokageFirmApp.service.ValidationService;

@RequestMapping("/customer")
@RestController
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ValidationService validationService;

    @PutMapping("/{customerId}/deposit")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<Boolean> depositMoney (
        @PathVariable("customerId") Long customerId,
        @RequestBody DepositMoneyDto depositMoneyDto
    ) throws CustomerNotFoundException {

        validationService.validateDepositMoneyRequest(customerId);

        customerService.depositMoneyForCustomer(customerId, depositMoneyDto.getAmount());

        return ResponseEntity.ok(true);
    }

    @PutMapping("/{customerId}/withdraw")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<Boolean> withdrawMoney (
        @PathVariable("customerId") Long customerId,
        @RequestBody WithdrawMoneyDto withdrawMoneyDto
    ) throws CustomerNotFoundException, NotSufficientBalanceToWithdraw {

        validationService.validateWithdrawMoneyRequest(customerId, withdrawMoneyDto.getAmount());

        customerService.withdrawMoneyForCustomer(
            customerId, withdrawMoneyDto.getAmount(), withdrawMoneyDto.getIBAN()
        );

        return ResponseEntity.ok(true);
    }
}
