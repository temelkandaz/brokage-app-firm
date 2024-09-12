package com.brokage.firm.brokageFirmApp.exception;

public class CanOnlySellOwnAssetsException extends Exception {
    
    public CanOnlySellOwnAssetsException(String errorMessage) {
        super(errorMessage);
    }
}
