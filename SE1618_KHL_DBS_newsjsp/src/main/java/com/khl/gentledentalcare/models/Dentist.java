/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khl.gentledentalcare.models;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ADMIN
 */
@Getter
@Setter
public class Dentist {

    private int DentistID;
    private String NameDentist;
    private String NumberPhoneDentist;
    private String DetailDentist;
    private String ImageDentist;
    private String DentistDescription;
    private String AcademicRank;
    private String titleDentist;

    public Dentist() {
    }

    public Dentist(int DentistID, String NameDentist, String NumberPhoneDentist, String DetailDentist, String ImageDentist, String DentistDescription, String AcademicRank, String titleDentist) {
        this.DentistID = DentistID;
        this.NameDentist = NameDentist;
        this.NumberPhoneDentist = NumberPhoneDentist;
        this.DetailDentist = DetailDentist;
        this.ImageDentist = ImageDentist;
        this.DentistDescription = DentistDescription;
        this.AcademicRank = AcademicRank;
        this.titleDentist = titleDentist;
    }

}
