package com.brokage.firm.brokageFirmApp.exception;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
