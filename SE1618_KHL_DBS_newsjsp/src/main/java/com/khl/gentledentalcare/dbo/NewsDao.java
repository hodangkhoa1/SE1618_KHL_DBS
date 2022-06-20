/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.models.News;
import com.khl.gentledentalcare.models.NewsDetail;
import com.khl.gentledentalcare.utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class NewsDao {

    public static ArrayList<News> getNews() {
        ArrayList<News> newsList = new ArrayList<>();
        Connection cn = null;
        News news = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT N.NewsID, nameOfNews, newsStatus, imagePath \n"
                        + "FROM News N, NewsDetail D\n"
                        + "WHERE N.NewsID = D.NewsID AND postDate < GETDATE()\n"
                        + "ORDER BY postDate DESC";
                PreparedStatement st = cn.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        String NewsID = rs.getString("NewsID");
                        String nameOfNews = rs.getString("nameOfNews");
                        int newsStatus = rs.getInt("newsStatus");
                        String imagePath = rs.getString("imagePath");
                        news = new News(NewsID, nameOfNews, imagePath, nameOfNews, newsStatus);
                        newsList.add(news);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return newsList;
    }

    public static NewsDetail getLastestNews() {
        NewsDetail newsDetail = null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT TOP 1 DetailID, NewsID, newsTitle, newsContent, postDate, imgPath, numberOfViews, poster\n"
                        + "FROM NewsDetail \n"
                        + "WHERE postDate < GETDATE()\n"
                        + "ORDER BY postDate DESC";
                PreparedStatement st = cn.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    String DetailID = rs.getString("DetailID");
                    String NewsID = rs.getString("NewsID");
                    String newsTitle = rs.getString("newsTitle");
                    String newsContent = rs.getString("newsContent");
                    Date postDate = rs.getDate("postDate");
                    String imgPath = rs.getString("imgPath");
                    String poster = rs.getString("poster");
                    int numberOfViews = rs.getInt("numberOfViews");
                    numberOfViews++;
                    newsDetail = new NewsDetail(DetailID, NewsID, newsTitle, newsContent, imgPath, poster, postDate, numberOfViews);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return newsDetail;
    }

    public static NewsDetail getNewsDetail(String newsID) {
        NewsDetail newsDetail = null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT DetailID, newsTitle, newsContent, postDate, imgPath, numberOfViews, poster\n"
                        + "FROM NewsDetail \n"
                        + "WHERE NewsID = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, newsID);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    String DetailID = rs.getString("DetailID");
                    String newsTitle = rs.getString("newsTitle");
                    String newsContent = rs.getString("newsContent");
                    String poster = rs.getString("poster");
                    int numberOfViews = rs.getInt("numberOfViews");
                    Date postDate = rs.getDate("postDate");
                    String imgPath = rs.getString("imgPath");
                    numberOfViews++;
                    newsDetail = new NewsDetail(DetailID, newsID, newsTitle, newsContent, imgPath, poster, postDate, numberOfViews);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return newsDetail;
    }

}
