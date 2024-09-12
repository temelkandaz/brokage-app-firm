package com.brokage.firm.brokageFirmApp.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

@Service
public class TimeService {
    
    public Timestamp convertStringToTimestamp(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try { 
            java.util.Date parsedDate = dateFormat.parse(date); 
      
            Timestamp timestamp = new Timestamp(parsedDate.getTime()); 

            return timestamp;
        } catch (ParseException e) { 
            e.printStackTrace(); 

            throw e;
        } 
    }
}
