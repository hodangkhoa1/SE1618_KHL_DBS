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
public class Booking {

    private String bookingID, userID, hospitalID, bookingNote;
    private int bookingStatus;
    private Timestamp bookingCreated;

    public Booking() {
    }

    public Booking(String bookingID, String userID, String hospitalID, String bookingNote, int bookingStatus, Timestamp bookingCreated) {
        this.bookingID = bookingID;
        this.userID = userID;
        this.hospitalID = hospitalID;
        this.bookingNote = bookingNote;
        this.bookingStatus = bookingStatus;
        this.bookingCreated = bookingCreated;
    }
}
