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
public class Notification {

    private String notifyID, userID, notifyType;
    private int notifyStatus;
    private Timestamp notifyCreated;

    public Notification() {
    }

    public Notification(String notifyID, String userID, String notifyType, int notifyStatus, Timestamp notifyCreated) {
        this.notifyID = notifyID;
        this.userID = userID;
        this.notifyType = notifyType;
        this.notifyStatus = notifyStatus;
        this.notifyCreated = notifyCreated;
    }
}
