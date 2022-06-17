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
public class MessageChat {

    private String roomID, chatContent;
    private Timestamp chatDate;

    public MessageChat() {
    }

    public MessageChat(String roomID, String chatContent, Timestamp chatDate) {
        this.roomID = roomID;
        this.chatContent = chatContent;
        this.chatDate = chatDate;
    }
}
