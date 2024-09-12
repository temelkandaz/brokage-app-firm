package com.brokage.firm.brokageFirmApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brokage.firm.brokageFirmApp.entity.Customer;
import com.brokage.firm.brokageFirmApp.repository.CustomerRepository;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomerById(long customerId){
        return customerRepository.findById(customerId);
    }

    public void depositMoneyForCustomer(Long customerId, int depositAmount) {
        Customer customer = this.getCustomerById(customerId);

        int newBalanceAmount = customer.getBalance() + depositAmount;

        customer.setBalance(newBalanceAmount);

        customerRepository.save(customer);
    }
}
