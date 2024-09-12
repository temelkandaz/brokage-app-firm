package com.brokage.firm.brokageFirmApp.exception;

public class NotHaveSufficientBalanceToProvideOrderPriceException extends Exception {
    
    public NotHaveSufficientBalanceToProvideOrderPriceException(String errorMessage) {
        super(errorMessage);
    }
}
