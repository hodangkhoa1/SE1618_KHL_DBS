package com.khl.gentledentalcare.models;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ASUS
 */
@Getter
@Setter
public class Employee {

    private String employeeID;
    private int insurance;
    private double salary;

    public Employee() {
    }

    public Employee(String employeeID, double salary, int insurance) {
        this.employeeID = employeeID;
        this.salary = salary;
        this.insurance = insurance;
    }
}
