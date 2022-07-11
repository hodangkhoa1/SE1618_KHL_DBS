package com.khl.gentledentalcare.models;

import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class DentistError {
    private String nameDentistError, subtitleDentist, numberPhoneDentistError, imageDentistError, dentistDescriptionError, academicRankError;
    
    public DentistError() {
        this.nameDentistError = "";
        this.subtitleDentist = "";
        this.numberPhoneDentistError = "";
        this.imageDentistError = "";
        this.dentistDescriptionError = "";
        this.academicRankError = "";
    }
}
