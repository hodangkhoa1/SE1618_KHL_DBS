package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.models.FeedBack;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class FeedBackFacade extends AbstractFeedBack<FeedBack> {

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final String SQL_GET_TOTAL_FEEDBACK = "SELECT COUNT(*) FROM FeedBack";

    private FeedBack getInfoAccountFromSQL(ResultSet resultSet) throws SQLException {
        String getFeedBackID = resultSet.getString("FeedBackID");
        String getBookingServiceID = resultSet.getString("BookingServiceID");
        int getNumberRating = resultSet.getInt("NumberRating");
        String getFeedBackContent = resultSet.getString("FeedBackContent");
        int getFeedBackStatus = resultSet.getInt("FeedBackStatus");
        Timestamp getFeedBackCreated = resultSet.getTimestamp("FeedBackCreated");

        return new FeedBack(getFeedBackID, getBookingServiceID, getFeedBackContent, getNumberRating, getFeedBackStatus, getFeedBackCreated);
    }

    @Override
    protected List<FeedBack> getAllFeedBack(Connection connection) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected boolean addFeedBack(Connection connection, FeedBack feedBack) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected boolean updateFeedBack(Connection connection, Object feedBackID) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

}
