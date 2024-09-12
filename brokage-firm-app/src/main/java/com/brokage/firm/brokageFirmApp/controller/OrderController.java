package com.brokage.firm.brokageFirmApp.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brokage.firm.brokageFirmApp.dto.CreateOrderDto;
import com.brokage.firm.brokageFirmApp.entity.Asset;
import com.brokage.firm.brokageFirmApp.entity.Customer;
import com.brokage.firm.brokageFirmApp.entity.Order;
import com.brokage.firm.brokageFirmApp.service.AssetService;
import com.brokage.firm.brokageFirmApp.service.CustomerService;
import com.brokage.firm.brokageFirmApp.service.OrderService;
import com.brokage.firm.brokageFirmApp.service.TimeService;

@RequestMapping("/order")
@RestController
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @Autowired
    private AssetService assetService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TimeService timeService;

    @PostMapping("/create")
    public ResponseEntity<Order> create(@RequestBody CreateOrderDto createOrderDto) {
        Asset asset = assetService.getAssetById(createOrderDto.getAssetId());

        if (asset == null) {
            return ResponseEntity.ok(new Order());
        }

        Customer customer = customerService.getCustomerById(createOrderDto.getCustomerId());

        if (customer == null) {
            return ResponseEntity.ok(new Order());
        }

        Order order = orderService.create(createOrderDto);

        return ResponseEntity.ok(order);
    }

    @GetMapping("/customer/{customerId}")
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
