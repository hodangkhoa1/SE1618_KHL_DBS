package com.khl.gentledentalcare.models;

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
    private int servicePrice, serviceStatus;

    public Services() {
    }

    public Services(String serviceID, String serviceName, String imageService, String descriptionService, int servicePrice, int serviceStatus) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.imageService = imageService;
        this.descriptionService = descriptionService;
        this.servicePrice = servicePrice;
        this.serviceStatus = serviceStatus;
    }
}
