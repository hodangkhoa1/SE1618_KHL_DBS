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

    private String feedBackID, bookingServiceID, feedBackContent;
    private int numberRating, feedBackStatus;
    private Timestamp feedBackCreated;

    public FeedBack() {
    }

    public FeedBack(String feedBackID, String bookingServiceID, String feedBackContent, int numberRating, int feedBackStatus, Timestamp feedBackCreated) {
        this.feedBackID = feedBackID;
        this.bookingServiceID = bookingServiceID;
        this.feedBackContent = feedBackContent;
        this.numberRating = numberRating;
        this.feedBackStatus = feedBackStatus;
        this.feedBackCreated = feedBackCreated;
    }
}
