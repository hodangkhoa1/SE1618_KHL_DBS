package com.khl.gentledentalcare.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dentist {

    private String dentistID, nameDentist, numberPhoneDentist, imageDentist, dentistDescription, academicRank;

    public Dentist() {
    }

    public Dentist(String dentistID, String nameDentist, String numberPhoneDentist, String imageDentist, String dentistDescription, String academicRank) {
        this.dentistID = dentistID;
        this.nameDentist = nameDentist;
        this.numberPhoneDentist = numberPhoneDentist;
        this.imageDentist = imageDentist;
        this.dentistDescription = dentistDescription;
        this.academicRank = academicRank;
    }
}
