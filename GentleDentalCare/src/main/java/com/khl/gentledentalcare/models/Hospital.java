package com.khl.gentledentalcare.models;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ASUS
 */
@Getter
@Setter
public class Hospital {

    private String hospitalID, hospitalName, hospitalPhone, hospitalAddress;
    private int hospitalStatus;

    public Hospital() {
    }

    public Hospital(String hospitalID, String hospitalName, String hospitalPhone, String hospitalAddress, int hospitalStatus) {
        this.hospitalID = hospitalID;
        this.hospitalName = hospitalName;
        this.hospitalPhone = hospitalPhone;
        this.hospitalAddress = hospitalAddress;
        this.hospitalStatus = hospitalStatus;
    }
}
