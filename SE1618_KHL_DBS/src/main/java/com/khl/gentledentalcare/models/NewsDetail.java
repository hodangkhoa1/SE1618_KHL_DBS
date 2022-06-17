package com.khl.gentledentalcare.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsDetail {

    private String newsDetailID, newsID, newsDetailContent, imageNewsDetail;

    public NewsDetail() {
    }

    public NewsDetail(String newsDetailID, String newsID, String newsDetailContent, String imageNewsDetail) {
        this.newsDetailID = newsDetailID;
        this.newsID = newsID;
        this.newsDetailContent = newsDetailContent;
        this.imageNewsDetail = imageNewsDetail;
    }
}
