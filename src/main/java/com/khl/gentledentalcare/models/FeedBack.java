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
public class FeedBack {

    private String feedBackID, bookingID, userID, feedBackContent;
    private int numberRating, feedBackStatus;
    private Timestamp feedBackCreated;

    public FeedBack() {
    }

    public FeedBack(String feedBackID, String bookingID, String userID, String feedBackContent, int numberRating, int feedBackStatus, Timestamp feedBackCreated) {
        this.feedBackID = feedBackID;
        this.bookingID = bookingID;
        this.userID = userID;
        this.feedBackContent = feedBackContent;
        this.numberRating = numberRating;
        this.feedBackStatus = feedBackStatus;
        this.feedBackCreated = feedBackCreated;
    }

}
