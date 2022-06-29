package com.khl.gentledentalcare.models;

import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class ServicesError {
    private String serviceNameError, servicePriceError, imageServiceError, descriptionServiceError;
    
    public ServicesError() {
        this.serviceNameError = "";
        this.servicePriceError = "";
        this.imageServiceError = "";
        this.descriptionServiceError = "";
    }
}
