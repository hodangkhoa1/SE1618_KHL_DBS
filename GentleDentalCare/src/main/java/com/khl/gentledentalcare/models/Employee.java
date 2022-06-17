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

    private String employeeID, userID;
    private int salary, insurance;

    public Employee() {
    }

    public Employee(String employeeID, String userID, int salary, int insurance) {
        this.employeeID = employeeID;
        this.userID = userID;
        this.salary = salary;
        this.insurance = insurance;
    }
}
