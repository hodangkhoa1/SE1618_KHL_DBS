/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khl.gentledentalcare.models;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ADMIN
 */
@Getter
@Setter
public class NewsDetail {

    private String DetailID, NewsID, newsTitle, newsContent, imgPath, porter;
    private Date postDate;
    private int numberOfViews;
    public NewsDetail() {
    }

    public NewsDetail(String DetailID, String NewsID, String newsTitle, String newsContent, String imgPath, String porter, Date postDate, int numberOfViews) {
        this.DetailID = DetailID;
        this.NewsID = NewsID;
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.imgPath = imgPath;
        this.porter = porter;
        this.postDate = postDate;
        this.numberOfViews = numberOfViews;
    }

   

}
