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

        int finalBalance = customer.getBalance() + depositAmount;

        customer.setBalance(finalBalance);

        customerRepository.save(customer);
    }

    public void withdrawMoneyForCustomer(Long customerId, int withdrawAmount, String IBAN) {
        Customer customer = this.getCustomerById(customerId);

        // send money to IBAN 

        int finalBalance = customer.getBalance() - withdrawAmount;

        customer.setBalance(finalBalance);

        customerRepository.save(customer);
    }
}
