package com.khl.gentledentalcare.models;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class News {

    private String newsID, nameOfNews, imageNews, subtitleNews;
    private int statusNews;
    private Timestamp postDate;

    public News() {
    }

    public News(String newsID, String nameOfNews, String imageNews, String subtitleNews, int statusNews, Timestamp postDate) {
        this.newsID = newsID;
        this.nameOfNews = nameOfNews;
        this.imageNews = imageNews;
        this.subtitleNews = subtitleNews;
        this.statusNews = statusNews;
        this.postDate = postDate;
    }
}
