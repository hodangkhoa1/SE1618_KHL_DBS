package com.khl.gentledentalcare.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dentist {

    private String dentistID, nameDentist, numberPhoneDentist, detailDentist, imageDentist, dentistDescription, academicRank, titleDentist;

    public Dentist(String dentistID, String nameDentist, String numberPhoneDentist, String detailDentist, String imageDentist, String dentistDescription, String academicRank, String titleDentist) {
        this.dentistID = dentistID;
        this.nameDentist = nameDentist;
        this.numberPhoneDentist = numberPhoneDentist;
        this.detailDentist = detailDentist;
        this.imageDentist = imageDentist;
        this.dentistDescription = dentistDescription;
        this.academicRank = academicRank;
        this.titleDentist = titleDentist;
    }
}
