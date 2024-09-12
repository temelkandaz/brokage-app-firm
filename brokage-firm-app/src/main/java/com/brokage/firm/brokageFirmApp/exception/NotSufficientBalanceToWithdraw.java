package com.brokage.firm.brokageFirmApp.exception;

public class NotSufficientBalanceToWithdraw extends Exception {
    public NotSufficientBalanceToWithdraw(String errorMessage) {
        super(errorMessage);
    }
}
