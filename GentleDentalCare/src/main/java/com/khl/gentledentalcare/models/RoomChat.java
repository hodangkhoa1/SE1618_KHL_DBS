package com.khl.gentledentalcare.models;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ASUS
 */
@Getter
@Setter
public class RoomChat {

    private String roomID, employeeID, userID;
    private int roomStatus;

    public RoomChat() {
    }

    public RoomChat(String roomID, String employeeID, String userID, int roomStatus) {
        this.roomID = roomID;
        this.employeeID = employeeID;
        this.userID = userID;
        this.roomStatus = roomStatus;
    }
}
