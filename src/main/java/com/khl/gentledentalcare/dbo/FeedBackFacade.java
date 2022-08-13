package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.models.FeedBack;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FeedBackFacade extends AbstractFeedBack<FeedBack> {

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final String SQL_GET_TOTAL_FEEDBACK = "SELECT COUNT(*) FROM FeedBack";
    private static final String SQL_INSERT_FEEDBACK = "INSERT INTO FeedBack(FeedBackID, BookingID, UserID, NumberRating, FeedBackContent) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_GET_FEEDBACK_BY_ID = "SELECT * FROM FeedBack WHERE BookingID = ? AND UserID = ?";
    private static final String SQL_GET_TOP_5_FEEDBACK = "SELECT TOP 5 * FROM FeedBack";
    private static final String SQL_GET_NEXT_5_FEEDBACK = "SELECT * FROM FeedBack ORDER BY FeedBackID OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY;";

    private FeedBack getInfoFeedBackFromSQL(ResultSet resultSet) throws SQLException {
        String getFeedBackID = resultSet.getString("FeedBackID");
        String getBookingID = resultSet.getString("BookingID");
        String getUserID = resultSet.getString("UserID");
        int getNumberRating = resultSet.getInt("NumberRating");
        String getFeedBackContent = resultSet.getString("FeedBackContent");
        int getFeedBackStatus = resultSet.getInt("FeedBackStatus");
        Timestamp getFeedBackCreated = resultSet.getTimestamp("FeedBackCreated");

        return new FeedBack(getFeedBackID, getBookingID, getUserID, getFeedBackContent, getNumberRating, getFeedBackStatus, getFeedBackCreated);
    }

    @Override
    protected List<FeedBack> getAllFeedBack(Connection connection, Object object, Object action) throws SQLException {
        ArrayList<FeedBack> feedBackAllList = new ArrayList<>();

        try {
            if (connection != null) {
                switch (action.toString()) {
                    case "GetTop5FeedBack":
                        preparedStatement = connection.prepareStatement(SQL_GET_TOP_5_FEEDBACK);
                        break;
                    case "GetNext5FeedBack":
                        preparedStatement = connection.prepareStatement(SQL_GET_NEXT_5_FEEDBACK);
                        preparedStatement.setInt(1, Integer.parseInt(object.toString()));
                        break;
                }

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    FeedBack feedBack = getInfoFeedBackFromSQL(resultSet);
                    feedBackAllList.add(feedBack);
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
        return feedBackAllList;
    }

    @Override
    protected boolean addFeedBack(Connection connection, FeedBack feedBack) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_INSERT_FEEDBACK);

                preparedStatement.setString(1, feedBack.getFeedBackID());
                preparedStatement.setString(2, feedBack.getBookingID());
                preparedStatement.setString(3, feedBack.getUserID());
                preparedStatement.setInt(4, feedBack.getNumberRating());
                preparedStatement.setString(5, feedBack.getFeedBackContent());
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
    protected int countFeedBack(Connection connection) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_GET_TOTAL_FEEDBACK);
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
    protected FeedBack getFeedBack(Connection connection, Object object, Object userID) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_GET_FEEDBACK_BY_ID);
                preparedStatement.setString(1, object.toString());
                preparedStatement.setString(2, userID.toString());
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return getInfoFeedBackFromSQL(resultSet);
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
