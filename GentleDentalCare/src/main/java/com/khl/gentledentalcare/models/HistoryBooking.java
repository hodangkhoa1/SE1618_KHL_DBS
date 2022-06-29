package com.khl.gentledentalcare.models;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ASUS
 */
@Getter
@Setter
public class HistoryBooking {
    private String bookingID, fullName, phoneNumber, address, service1, service2;
    private Timestamp bookingCreated;

    public HistoryBooking() {
    }

    public HistoryBooking(String bookingID, String fullName, String phoneNumber, String address, String service1, String service2, Timestamp bookingCreated) {
        this.bookingID = bookingID;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.service1 = service1;
        this.service2 = service2;
        this.bookingCreated = bookingCreated;
    }
}
