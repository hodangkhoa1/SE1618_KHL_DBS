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
public class Slot {

    private String slotID;
    private Time slotStart;

    public Slot() {
    }

    public Slot(String slotID, Time slotStart) {
        this.slotID = slotID;
        this.slotStart = slotStart;
    }
}
