package com.khl.gentledentalcare.models;

import java.sql.Date;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

    private String userID, fullName, userPassword, userEmail, userAddress, userPhone, gender, imageAvatar, colorAvatar, defaultAvatar;
    private Date dateOfBirth;
    private int userRole, userStatus;
    private Timestamp accountCreated;

    public Account() {
    }

    public Account(String userID, String fullName, String userPassword, String userEmail, String userAddress, String userPhone, String gender, String imageAvatar, String colorAvatar, String defaultAvatar, Date dateOfBirth, int userRole, int userStatus, Timestamp accountCreated) {
        this.userID = userID;
        this.fullName = fullName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.gender = gender;
        this.imageAvatar = imageAvatar;
        this.colorAvatar = colorAvatar;
        this.defaultAvatar = defaultAvatar;
        this.dateOfBirth = dateOfBirth;
        this.userRole = userRole;
        this.userStatus = userStatus;
        this.accountCreated = accountCreated;
    }
}
