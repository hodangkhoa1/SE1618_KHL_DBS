package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.models.News;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsFacade extends AbstractNews<News> {

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final String SQL_GET_TOP_8_NEWS = "SELECT TOP 8 * FROM News WHERE PostDate < GETDATE() ORDER BY PostDate DESC";
    private static final String SQL_GET_NEXT_8_NEWS = "SELECT * FROM News ORDER BY NewsID OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY;";
    private static final String SQL_PAGING_NEWS = "SELECT * FROM News ORDER BY NewsID OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY;";
    private static final String SQL_SEARCH_NEWS_BY_NAME = "SELECT * FROM News WHERE NameOfNews LIKE ?";
    private static final String SQL_ADD_NEWS = "INSERT INTO News(NewsID, NameOfNews, ImageNews, SubtitleNews, NewsDetailContent) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_EDIT_NEWS = "UPDATE News SET NameOfNews = ?, ImageNews = ?, SubtitleNews = ?, NewsDetailContent = ? WHERE NewsID = ?";
    private static final String SQL_NEWS_STATUS = "UPDATE News SET StatusNews = ? WHERE NewsID = ?";
    private static final String SQL_GET_TOTAL_NEWS = "SELECT COUNT(*) FROM News";
    private static final String SQL_CHECK_NEWS = "SELECT * FROM News WHERE NewsID = ?";
    private static final String SQL_GET_NEWS_LATEST = "SELECT TOP 4 * FROM News ORDER BY NEWID()";

    private News getInfoNewsFromSQL(ResultSet resultSet) throws SQLException {
        String getNewsID = resultSet.getString("NewsID");
        String getNameOfNews = resultSet.getString("NameOfNews");
        byte[] getImageNews = resultSet.getBytes("ImageNews");
        String getSubtitleNews = resultSet.getString("SubtitleNews");
        String getNewsDetailContent = resultSet.getString("NewsDetailContent");
        int getStatusNews = resultSet.getInt("StatusNews");
        Timestamp getPostDate = resultSet.getTimestamp("PostDate");

        return new News(getNewsID, getNameOfNews, Base64.encode(getImageNews), getSubtitleNews, getNewsDetailContent, getStatusNews, getPostDate);
    }

    @Override
    protected List<News> getNews(Connection connection, Object value, Object action) throws SQLException {
        ArrayList<News> newsList = new ArrayList<>();

        try {
            if (connection != null) {
                switch (action.toString()) {
                    case "Top8News":
                        preparedStatement = connection.prepareStatement(SQL_GET_TOP_8_NEWS);
                        break;
                    case "GetNext8Course":
                        preparedStatement = connection.prepareStatement(SQL_GET_NEXT_8_NEWS);
                        preparedStatement.setInt(1, (Integer.parseInt(value.toString())));
                        break;
                    case "PagingNews":
                        preparedStatement = connection.prepareStatement(SQL_PAGING_NEWS);
                        preparedStatement.setInt(1, ((int) value - 1) * 5);
                        break;
                    case "SearchByName":
                        preparedStatement = connection.prepareStatement(SQL_SEARCH_NEWS_BY_NAME);
                        String[] cutText = value.toString().split("\\.");
                        preparedStatement.setString(1, "%" + cutText[0] + "%");
                        break;
                    case "GetNewsLatest":
                        preparedStatement = connection.prepareStatement(SQL_GET_NEWS_LATEST);
                        break;
                }

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    News news = getInfoNewsFromSQL(resultSet);
                    newsList.add(news);
                }
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return newsList;
    }

    @Override
    protected boolean addNews(Connection connection, News news) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_ADD_NEWS);
                preparedStatement.setString(1, news.getNewsID());
                preparedStatement.setString(2, news.getNameOfNews());
                preparedStatement.setBytes(3, Base64.decode(news.getImageNews()));
                preparedStatement.setString(4, news.getSubtitleNews());
                preparedStatement.setString(5, news.getNewsDetailContent());
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
    protected boolean updateNews(Connection connection, News news, Object object) throws SQLException {
        try {
            if (connection != null) {
                switch (object.toString()) {
                    case "EditStatus":
                        preparedStatement = connection.prepareStatement(SQL_NEWS_STATUS);
                        preparedStatement.setInt(1, news.getStatusNews());
                        preparedStatement.setString(2, news.getNewsID());
                        break;
                    case "EditNews":
                        preparedStatement = connection.prepareStatement(SQL_EDIT_NEWS);
                        preparedStatement.setString(1, news.getNameOfNews());
                        preparedStatement.setBytes(2, Base64.decode(news.getImageNews()));
                        preparedStatement.setString(3, news.getSubtitleNews());
                        preparedStatement.setString(4, news.getNewsDetailContent());
                        preparedStatement.setString(5, news.getNewsID());
                        break;
                }

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
    protected int countNews(Connection connection) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_GET_TOTAL_NEWS);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    return resultSet.getInt(1);
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
        return 0;
    }

    @Override
    protected News checkNews(Connection connection, Object object) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_CHECK_NEWS);
                preparedStatement.setString(1, object.toString());
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return getInfoNewsFromSQL(resultSet);
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

}
