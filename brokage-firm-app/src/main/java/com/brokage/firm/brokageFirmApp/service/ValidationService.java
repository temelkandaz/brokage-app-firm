package com.brokage.firm.brokageFirmApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brokage.firm.brokageFirmApp.dto.CreateOrderDto;
import com.brokage.firm.brokageFirmApp.entity.Asset;
import com.brokage.firm.brokageFirmApp.entity.Customer;
import com.brokage.firm.brokageFirmApp.entity.Order;
import com.brokage.firm.brokageFirmApp.entity.OrderSide;
import com.brokage.firm.brokageFirmApp.entity.OrderStatus;
import com.brokage.firm.brokageFirmApp.exception.AssetNotFoundException;
import com.brokage.firm.brokageFirmApp.exception.CanOnlySellOwnAssetsException;
import com.brokage.firm.brokageFirmApp.exception.CantBuyOwnAssetsException;
import com.brokage.firm.brokageFirmApp.exception.CustomerNotFoundException;
import com.brokage.firm.brokageFirmApp.exception.NotEnoughUsableSizeToBuyException;
import com.brokage.firm.brokageFirmApp.exception.NotEnoughUsableSizeToSellException;
import com.brokage.firm.brokageFirmApp.exception.NotHaveSufficientBalanceToProvideOrderPriceException;
import com.brokage.firm.brokageFirmApp.exception.NotSufficientBalanceToWithdraw;
import com.brokage.firm.brokageFirmApp.exception.OrderNotFoundException;
import com.brokage.firm.brokageFirmApp.exception.OrderNotInPendingStatusException;

@Service
public class ValidationService {
    
    @Autowired
    private AssetService assetService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    public void validateCreateOrderRequest(CreateOrderDto createOrderDto) 
        throws CustomerNotFoundException, AssetNotFoundException, CantBuyOwnAssetsException, CanOnlySellOwnAssetsException, NotEnoughUsableSizeToBuyException, NotEnoughUsableSizeToSellException, NotHaveSufficientBalanceToProvideOrderPriceException {

        Asset asset = assetService.getAssetById(createOrderDto.getAssetId());

        if (asset == null) {
            throw new AssetNotFoundException("No asset is found with id: " + createOrderDto.getAssetId());
        }

        Customer customer = customerService.getCustomerById(createOrderDto.getCustomerId());

        if (customer == null) {
            throw new CustomerNotFoundException("No customer is found with id: " + createOrderDto.getCustomerId());
        }

        if (createOrderDto.getOrderSide() == OrderSide.BUY) {
            if (asset.getCustomer().getId() == customer.getId()){
                throw new CantBuyOwnAssetsException(
                    "Customer with id: " + customer.getId() + " can't buy owned asset with id: " + asset.getCustomer().getId()
                );
            }

            if (asset.getUsableSize() < createOrderDto.getSize()) {
                throw new NotEnoughUsableSizeToBuyException(
                    "Asset with id: " + asset.getId() + " doesn't have enough usable size to match the order size"
                );
            }

            if (customer.getBalance() < createOrderDto.getPrice()) {
                throw new NotHaveSufficientBalanceToProvideOrderPriceException(
                    "Customer with id: " + customer.getId() + " doesn't have the sufficient balance to provide order price."
                );
            }
        }

        if (createOrderDto.getOrderSide() == OrderSide.SELL){
            if (asset.getCustomer().getId() != customer.getId()){
                throw new CanOnlySellOwnAssetsException(
                    "Customer with id: " + customer.getId() + " can't sell not owned asset with id: " + asset.getCustomer().getId()
                );
            }

            if (asset.getUsableSize() < createOrderDto.getSize()) {
                throw new NotEnoughUsableSizeToSellException(
                    "Asset with id: " + asset.getId() + " doesn't have enough usable size to match the order size"
                );
            }
        }
    }

    public void validateCancelOrderRequest(Long orderId) 
        throws OrderNotFoundException, OrderNotInPendingStatusException {

        Order order = orderService.getOrderById(orderId);

        if (order == null) {
            throw new OrderNotFoundException("No order is found with id: " + orderId);
        }

        if (order.getOrderStatus() != OrderStatus.PENDING) {
            throw new OrderNotInPendingStatusException("Order with id: " + orderId + " is not in PENDING status.");
        }
    }

    public void validateDepositMoneyRequest(Long customerId) throws CustomerNotFoundException {

        validateCustomerExistsWithCustomerId(customerId);
    }

    public void validateWithdrawMoneyRequest(Long customerId, int withdrawAmount) throws CustomerNotFoundException, NotSufficientBalanceToWithdraw {

        validateCustomerExistsWithCustomerId(customerId);

        Customer customer = customerService.getCustomerById(customerId);

        if (customer.getBalance() < withdrawAmount) {
            throw new NotSufficientBalanceToWithdraw("Customer doesn't have enough balance to withdraw the requested amount!");
        }
    }

    private void validateCustomerExistsWithCustomerId(Long customerId) throws CustomerNotFoundException {

        Customer customer = customerService.getCustomerById(customerId);

        if (customer == null) {
            throw new CustomerNotFoundException("No customer is found with id: " + customerId);
        }
    }
}
