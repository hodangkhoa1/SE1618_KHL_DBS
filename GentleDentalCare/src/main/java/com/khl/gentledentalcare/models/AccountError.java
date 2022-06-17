package com.khl.gentledentalcare.models;

import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class AccountError {

    private String fullNameError, passwordError, emailError, newPasswordError, confirmPasswordError, phoneNumberError, addressError, verifySMSError;
    
    public AccountError() {
        this.fullNameError = "";
        this.passwordError = "";
        this.newPasswordError = "";
        this.confirmPasswordError = "";
        this.emailError = "";
        this.phoneNumberError = "";
        this.addressError = "";
        this.verifySMSError = "";
    }
}
