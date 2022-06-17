package com.khl.gentledentalcare.models;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ASUS
 */
@Getter
@Setter
public class ServiceType {

    private String serviceTypeID, serviceTypeName;

    public ServiceType() {
    }

    public ServiceType(String serviceTypeID, String serviceTypeName) {
        this.serviceTypeID = serviceTypeID;
        this.serviceTypeName = serviceTypeName;
    }
}
