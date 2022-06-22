package com.khl.gentledentalcare.models;

import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class HospitalError {

    private String hospitalName, hospitalPhone, hospitalAddress;

    public HospitalError() {
        this.hospitalName = "";
        this.hospitalPhone = "";
        this.hospitalAddress = "";
    }
}
