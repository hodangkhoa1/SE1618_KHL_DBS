package com.khl.gentledentalcare.models;

import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
public class NewsError {

    private String nameOfNewsError, imageNewsError, subtitleNewsError, newsDetailContentError;
    
    public NewsError() {
        this.nameOfNewsError = "";
        this.imageNewsError = "";
        this.subtitleNewsError = "";
        this.newsDetailContentError = "";
    }
}
