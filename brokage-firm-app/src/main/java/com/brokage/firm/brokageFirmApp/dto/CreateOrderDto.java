package com.brokage.firm.brokageFirmApp.dto;

import com.brokage.firm.brokageFirmApp.entity.OrderSide;

public class CreateOrderDto {
    
    private int customerId;

    private int assetId;

    private OrderSide orderSide;

    private int size;

    private int price;
    
    public int getCustomerId() {
        return customerId;
    }

    public int getAssetId() {
        return assetId;
    }

    public OrderSide getOrderSide() {
        return orderSide;
    }

    public int getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }
}
