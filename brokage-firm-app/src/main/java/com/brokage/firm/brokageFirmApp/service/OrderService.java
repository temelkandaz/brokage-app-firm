package com.brokage.firm.brokageFirmApp.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brokage.firm.brokageFirmApp.dto.CreateOrderDto;
import com.brokage.firm.brokageFirmApp.entity.Asset;
import com.brokage.firm.brokageFirmApp.entity.Currency;
import com.brokage.firm.brokageFirmApp.entity.Customer;
import com.brokage.firm.brokageFirmApp.entity.Order;
import com.brokage.firm.brokageFirmApp.entity.OrderStatus;
import com.brokage.firm.brokageFirmApp.repository.AssetRepository;
import com.brokage.firm.brokageFirmApp.repository.CustomerRepository;
import com.brokage.firm.brokageFirmApp.repository.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Order create(CreateOrderDto createOrderDto) {
        Asset asset = assetRepository.findById(createOrderDto.getAssetId());
        Customer customer = customerRepository.findById(createOrderDto.getCustomerId());

        Order order = new Order();

        order.setAsset(asset);
        order.setAssetName(asset.getAssetName());
        order.setCustomer(customer);
        order.setOrderSide(createOrderDto.getOrderSide());
        order.setSize(createOrderDto.getSize());
        order.setPrice(createOrderDto.getPrice());
        order.setCurrency(Currency.TRY);
        order.setOrderStatus(OrderStatus.PENDING);

        orderRepository.save(order);

        return order;
    }

    public Order getOrderById(long orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> getOrdersByCustomerIdAndDateRange(
        long customerId, Timestamp fromDate, Timestamp toDate
    ) {
        return orderRepository.findByCustomerIdAndCreatedAtBetween(customerId, fromDate, toDate);
    }

    public Order cancelPendingOrder(long orderId) {
        Order order = orderRepository.findById(orderId);

        order.setOrderStatus(OrderStatus.CANCELLED);

        orderRepository.save(order);

        return order;
    }
}
