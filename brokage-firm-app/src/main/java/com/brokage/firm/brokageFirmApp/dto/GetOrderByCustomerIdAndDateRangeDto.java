package com.brokage.firm.brokageFirmApp.dto;

import java.sql.Timestamp;

public class GetOrderByCustomerIdAndDateRangeDto {

    private Timestamp fromDate;

    private Timestamp toDate;

    public Timestamp getFromDate() {
        return fromDate;
    }

    public Timestamp getToDate() {
        return toDate;
    }
}
