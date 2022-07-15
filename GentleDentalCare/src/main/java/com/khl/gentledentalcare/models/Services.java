package com.khl.gentledentalcare.models;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ASUS
 */
@Getter
@Setter
public class Services {

    private String serviceID, serviceName, imageService, descriptionService;
    private int serviceStatus;
    private double servicePrice;
    private Timestamp serviceCreated;

    public Services() {
    }

    public Services(String serviceID, String serviceName, String imageService, String descriptionService, double servicePrice, int serviceStatus, Timestamp serviceCreated) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.imageService = imageService;
        this.descriptionService = descriptionService;
        this.servicePrice = servicePrice;
        this.serviceStatus = serviceStatus;
        this.serviceCreated = serviceCreated;
    }
}
