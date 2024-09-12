package com.brokage.firm.brokageFirmApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brokage.firm.brokageFirmApp.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findById(long customerId);
}
