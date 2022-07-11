package com.khl.gentledentalcare.models;

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
    private int serviceSlotStatus;

    public ServiceSlot() {
    }

    public ServiceSlot(String slotServiceID, String serviceID, String slotID, int serviceSlotStatus) {
        this.slotServiceID = slotServiceID;
        this.serviceID = serviceID;
        this.slotID = slotID;
        this.serviceSlotStatus = serviceSlotStatus;
    }
}
