package com.khl.gentledentalcare.models;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ASUS
 */
@Getter
@Setter
public class BookingService {

    private String bookingServiceID, serviceID, bookingID, slotServiceID;
    private Date bookingDate;
    private int bsStatus;

    public BookingService() {
    }

    public BookingService(String bookingServiceID, String serviceID, String bookingID, String slotServiceID, Date bookingDate, int bsStatus) {
        this.bookingServiceID = bookingServiceID;
        this.serviceID = serviceID;
        this.bookingID = bookingID;
        this.slotServiceID = slotServiceID;
        this.bookingDate = bookingDate;
        this.bsStatus = bsStatus;
    }
}
