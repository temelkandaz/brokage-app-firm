package com.brokage.firm.brokageFirmApp.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brokage.firm.brokageFirmApp.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerIdAndCreatedAtBetween(
        long customerId, Timestamp fromDate, Timestamp toDate
    );
}
