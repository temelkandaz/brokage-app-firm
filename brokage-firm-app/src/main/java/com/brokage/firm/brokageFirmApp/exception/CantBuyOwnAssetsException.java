package com.brokage.firm.brokageFirmApp.exception;

public class CantBuyOwnAssetsException extends Exception {
    
    public CantBuyOwnAssetsException(String errorMessage) {
        super(errorMessage);
    }
}
