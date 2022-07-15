package com.khl.gentledentalcare.models;

import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class EmployeeError {

    private String insuranceError, salaryError;

    public EmployeeError() {
        this.insuranceError = "";
        this.salaryError = "";
    }
}
