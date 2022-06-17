package com.khl.gentledentalcare.models;

import java.sql.Time;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ASUS
 */
@Getter
@Setter
public class ServiceSlot {

    private String slotServiceID, serviceID, slotID;
    private Time slotStart, slotEnd;
    private int slotStatus;

    public ServiceSlot() {
    }

    public ServiceSlot(String slotServiceID, String serviceID, String slotID, Time slotStart, Time slotEnd, int slotStatus) {
        this.slotServiceID = slotServiceID;
        this.serviceID = serviceID;
        this.slotID = slotID;
        this.slotStart = slotStart;
        this.slotEnd = slotEnd;
        this.slotStatus = slotStatus;
    }
}
