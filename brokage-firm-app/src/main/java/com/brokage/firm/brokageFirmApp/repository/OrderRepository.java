package com.brokage.firm.brokageFirmApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brokage.firm.brokageFirmApp.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
