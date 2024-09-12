package com.brokage.firm.brokageFirmApp.exception;

public class OrderNotInPendingStatusException extends Exception {
    public OrderNotInPendingStatusException(String errorMessage) {
        super(errorMessage);
    }
}
