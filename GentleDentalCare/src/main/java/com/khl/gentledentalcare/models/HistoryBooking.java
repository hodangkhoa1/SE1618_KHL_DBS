package com.khl.gentledentalcare.models;

import java.sql.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ASUS
 */
@Getter
@Setter
public class HistoryBooking {

    private String bookingID, userId, fullName, phoneNumber, address;
    private List<Services> serviceList;
    private int bookingStatus;
    private Date bookingDate;

    public HistoryBooking() {
    }

    public HistoryBooking(String bookingID, String userId, String fullName, String phoneNumber, String address, List<Services> serviceList, int bookingStatus, Date bookingDate) {
        this.bookingID = bookingID;
        this.userId = userId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.serviceList = serviceList;
        this.bookingStatus = bookingStatus;
        this.bookingDate = bookingDate;
    }

}
