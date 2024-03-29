package com.khl.gentledentalcare.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dentist {

    private String dentistID, nameDentist, subtitleDentist, numberPhoneDentist, imageDentist, dentistDescription, academicRank;
    private int dentistStatus;

    public Dentist() {
    }

    public Dentist(String dentistID, String nameDentist, String subtitleDentist, String numberPhoneDentist, String imageDentist, String dentistDescription, String academicRank, int dentistStatus) {
        this.dentistID = dentistID;
        this.nameDentist = nameDentist;
        this.subtitleDentist = subtitleDentist;
        this.numberPhoneDentist = numberPhoneDentist;
        this.imageDentist = imageDentist;
        this.dentistDescription = dentistDescription;
        this.academicRank = academicRank;
        this.dentistStatus = dentistStatus;
    }

}
