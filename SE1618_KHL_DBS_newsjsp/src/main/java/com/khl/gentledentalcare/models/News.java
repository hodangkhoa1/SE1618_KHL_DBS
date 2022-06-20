/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khl.gentledentalcare.models;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ADMIN
 */
@Getter
@Setter
public class News {

    private String NewsID, nameOfnews, imagePath, newsContenShort;
    private int newsStatus;

    public News() {
    }

    public News(String NewsID, String nameOfnews, String imagePath, String newsContenShort, int newsStatus) {
        this.NewsID = NewsID;
        this.nameOfnews = nameOfnews;
        this.imagePath = imagePath;
        this.newsContenShort = newsContenShort;
        this.newsStatus = newsStatus;
    }

  

}
