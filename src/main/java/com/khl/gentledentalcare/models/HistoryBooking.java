package com.khl.gentledentalcare.models;

import java.sql.Date;
import java.sql.Timestamp;
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

    private String bookingID, userId, fullName, phoneNumber, address, bookingNote;
    private List<Services> serviceList;
    private List<Slot> slotList;
    private List<Date> bookingDateList;
    private int bookingStatus;
    private Timestamp bookingCreated;

    public HistoryBooking() {
    }

    public HistoryBooking(String bookingID, String userId, String fullName, String phoneNumber, String address, String bookingNote, List<Services> serviceList, List<Slot> slotList, List<Date> bookingDateList, int bookingStatus, Timestamp bookingCreated) {
        this.bookingID = bookingID;
        this.userId = userId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.bookingNote = bookingNote;
        this.serviceList = serviceList;
        this.slotList = slotList;
        this.bookingDateList = bookingDateList;
        this.bookingStatus = bookingStatus;
        this.bookingCreated = bookingCreated;
    }

}
