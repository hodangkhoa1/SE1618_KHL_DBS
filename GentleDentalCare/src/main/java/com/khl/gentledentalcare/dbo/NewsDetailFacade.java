package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.models.NewsDetail;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsDetailFacade extends AbstractNewsDetail<NewsDetail> {

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final String SQL_GET_NEWS_DETAIL_BY_ID = "SELECT * FROM NewsDetail WHERE NewsDetailID = ?";
    private static final String SQL_ADD_NEWS_DETAIL = "INSERT INTO NewsDetail(NewsDetailID, NewsID, NewsDetailContent, ImageNewsDetail) VALUES(?, ?, ?, ?)";
    private static final String SQL_EDIT_NEWS_DETAIL = "UPDATE NewsDetail SET NewsDetailContent = ?, ImageNewsDetail = ? WHERE NewsID = ?";

    private NewsDetail getInfoNewsDetailFromSQL(ResultSet resultSet) throws SQLException {
        String getNewsDetailID = resultSet.getString("NewsDetailID");
        String getNewsID = resultSet.getString("NewsID");
        String getNewsDetailContent = resultSet.getString("NewsDetailContent");
        byte[] getImageNewsDetail = resultSet.getBytes("ImageNewsDetail");

        return new NewsDetail(getNewsDetailID, getNewsID, getNewsDetailContent, Base64.encode(getImageNewsDetail));
    }

    @Override
    protected NewsDetail getNewsDetail(Connection connection, Object newsDetailID) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_GET_NEWS_DETAIL_BY_ID);
                preparedStatement.setString(1, newsDetailID.toString());
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return getInfoNewsDetailFromSQL(resultSet);
                }
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    @Override
    protected boolean addNewsDetail(Connection connection, NewsDetail newsDetail) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_ADD_NEWS_DETAIL);
                preparedStatement.setString(1, newsDetail.getNewsDetailID());
                preparedStatement.setString(2, newsDetail.getNewsID());
                preparedStatement.setString(3, newsDetail.getNewsDetailContent());
                preparedStatement.setBytes(4, Base64.decode(newsDetail.getImageNewsDetail()));
                preparedStatement.executeUpdate();
                return true;
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }

    @Override
    protected boolean updateNewsDetail(Connection connection, NewsDetail newsDetail) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_EDIT_NEWS_DETAIL);
                preparedStatement.setString(1, newsDetail.getNewsDetailContent());
                preparedStatement.setBytes(2, Base64.decode(newsDetail.getImageNewsDetail()));
                preparedStatement.setString(3, newsDetail.getNewsID());
                preparedStatement.executeUpdate();
                return true;
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }

}
