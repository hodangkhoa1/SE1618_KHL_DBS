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
public class Appointment {

    private String bookingID, userId, fullName, imageAvatar, hospitalName;
    private List<Services> serviceList;
    private List<Slot> slotList;
    private Date bookingDate;
    private int bookingStatus;

    public Appointment() {
    }

    public Appointment(String bookingID, String userId, String fullName, String imageAvatar, String hospitalName, List<Services> serviceList, List<Slot> slotList, Date bookingDate, int bookingStatus) {
        this.bookingID = bookingID;
        this.userId = userId;
        this.fullName = fullName;
        this.imageAvatar = imageAvatar;
        this.hospitalName = hospitalName;
        this.serviceList = serviceList;
        this.slotList = slotList;
        this.bookingDate = bookingDate;
        this.bookingStatus = bookingStatus;
    }
}
