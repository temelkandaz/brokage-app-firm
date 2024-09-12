package com.brokage.firm.brokageFirmApp.exception;

public class CustomerNotFoundException extends Exception {
    
    public CustomerNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
