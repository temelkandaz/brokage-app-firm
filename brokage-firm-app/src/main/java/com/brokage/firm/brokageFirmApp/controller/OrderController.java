package com.brokage.firm.brokageFirmApp.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brokage.firm.brokageFirmApp.dto.CreateOrderDto;
import com.brokage.firm.brokageFirmApp.entity.Order;
import com.brokage.firm.brokageFirmApp.exception.AssetNotFoundException;
import com.brokage.firm.brokageFirmApp.exception.CanOnlySellOwnAssetsException;
import com.brokage.firm.brokageFirmApp.exception.CantBuyOwnAssetsException;
import com.brokage.firm.brokageFirmApp.exception.CustomerNotFoundException;
import com.brokage.firm.brokageFirmApp.exception.NotEnoughUsableSizeToBuyException;
import com.brokage.firm.brokageFirmApp.exception.NotEnoughUsableSizeToSellException;
import com.brokage.firm.brokageFirmApp.exception.NotHaveSufficientBalanceToProvideOrderPriceException;
import com.brokage.firm.brokageFirmApp.exception.OrderNotFoundException;
import com.brokage.firm.brokageFirmApp.exception.OrderNotInPendingStatusException;
import com.brokage.firm.brokageFirmApp.service.OrderService;
import com.brokage.firm.brokageFirmApp.service.TimeService;
import com.brokage.firm.brokageFirmApp.service.ValidationService;

@RequestMapping("/order")
@RestController
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private TimeService timeService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<Order> create(
        @RequestBody CreateOrderDto createOrderDto
    ) throws CustomerNotFoundException, AssetNotFoundException, CantBuyOwnAssetsException, CanOnlySellOwnAssetsException, NotEnoughUsableSizeToBuyException, NotEnoughUsableSizeToSellException, NotHaveSufficientBalanceToProvideOrderPriceException {

        validationService.validateCreateOrderRequest(createOrderDto);

        Order order = orderService.create(createOrderDto);

        return ResponseEntity.ok(order);
    }

    @PutMapping("/{orderId}/cancel")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<Order> cancel(@PathVariable("orderId") Long orderId) 
        throws OrderNotFoundException, OrderNotInPendingStatusException {

        validationService.validateCancelOrderRequest(orderId);

        Order order = orderService.cancelPendingOrder(orderId);

        return ResponseEntity.ok(order);
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<List<Order>> getOrdersByCustomerIdAndDateRange(
        @PathVariable("customerId") Long customerId,
        @RequestParam String fromDate,
        @RequestParam String toDate
    ) throws ParseException {
        Timestamp fromDateTimestamp = timeService.convertStringToTimestamp(fromDate);
        Timestamp toDateTimestamp = timeService.convertStringToTimestamp(toDate);

        List<Order> orders = orderService.getOrdersByCustomerIdAndDateRange(
            customerId, 
            fromDateTimestamp,
            toDateTimestamp
        );

        return ResponseEntity.ok(orders);
    }
}
