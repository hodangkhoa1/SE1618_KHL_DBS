package com.khl.gentledentalcare.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedBackInfo {

    private String feedBackID, feedBackContent, hospitalName, imageAvatar, fullName, gender, phoneNumber, email, address, colorAvatar, defaultAvatar;
    private Date dateOfBirth;
    private int numberRating;
    private List<Services> serviceList;
    private Timestamp feedBackCreated;

    public FeedBackInfo() {
    }

    public FeedBackInfo(String feedBackID, String feedBackContent, String hospitalName, String imageAvatar, String fullName, String gender, String phoneNumber, String email, String address, String colorAvatar, String defaultAvatar, Date dateOfBirth, int numberRating, List<Services> serviceList, Timestamp feedBackCreated) {
        this.feedBackID = feedBackID;
        this.feedBackContent = feedBackContent;
        this.hospitalName = hospitalName;
        this.imageAvatar = imageAvatar;
        this.fullName = fullName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.colorAvatar = colorAvatar;
        this.defaultAvatar = defaultAvatar;
        this.dateOfBirth = dateOfBirth;
        this.numberRating = numberRating;
        this.serviceList = serviceList;
        this.feedBackCreated = feedBackCreated;
    }

}
