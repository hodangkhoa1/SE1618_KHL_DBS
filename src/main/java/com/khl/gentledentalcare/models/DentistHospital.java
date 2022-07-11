package com.khl.gentledentalcare.models;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ASUS
 */
@Getter
@Setter
public class DentistHospital {
    private String dentistHospitalID, hospitalID, dentistID;

    public DentistHospital() {
    }

    public DentistHospital(String dentistHospitalID, String hospitalID, String dentistID) {
        this.dentistHospitalID = dentistHospitalID;
        this.hospitalID = hospitalID;
        this.dentistID = dentistID;
    }
}
