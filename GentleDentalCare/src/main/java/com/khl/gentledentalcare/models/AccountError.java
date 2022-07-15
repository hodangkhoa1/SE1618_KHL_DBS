package com.khl.gentledentalcare.models;

import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class AccountError {

    private String fullNameError, genderError, dateOfBirthError, imageAvatarError, passwordError, emailError, newPasswordError, confirmPasswordError, phoneNumberError, addressError, verifySMSError;
    
    public AccountError() {
        this.fullNameError = "";
        this.genderError = "";
        this.dateOfBirthError = "";
        this.imageAvatarError = "";
        this.passwordError = "";
        this.newPasswordError = "";
        this.confirmPasswordError = "";
        this.emailError = "";
        this.phoneNumberError = "";
        this.addressError = "";
        this.verifySMSError = "";
    }
}
