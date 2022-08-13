package com.khl.gentledentalcare.models;

import lombok.Data;

@Data
public class BookingError {

    private String hospitalError, serviceSlotError;

    public BookingError() {
        this.hospitalError = "";
        this.serviceSlotError = "";
    }
}
