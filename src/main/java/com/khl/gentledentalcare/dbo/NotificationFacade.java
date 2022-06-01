package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.models.Notification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class NotificationFacade extends AbstractNotification<Notification> {

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final String SQL_GET_ALL_NOTIFICATION = "SELECT * FROM Notify WHERE UserID = ?";
    private static final String SQL_INSERT_NOTIFICATION = "INSERT INTO Notify(NotifyID, UserID, NotifyType) VALUES(?, ?, ?)";
    private static final String SQL_UPDATE_NOTIFICATION = "UPDATE Notify SET NotifyStatus = 1 WHERE NotifyID = ? AND UserID = ?";
    private static final String SQL_GET_TOTAL_NOTIFICATION = "SELECT COUNT(*) FROM Notify WHERE UserID = ? AND NotifyStatus = 0";

    private Notification getInfoNotificationFromSQL(ResultSet resultSet) throws SQLException {
        String getNotifyID = resultSet.getString("NotifyID");
        String getUserID = resultSet.getString("UserID");
        String getNotifyType = resultSet.getString("NotifyType");
        int getNotifyStatus = resultSet.getInt("NotifyStatus");
        Timestamp getNotifyCreated = resultSet.getTimestamp("NotifyCreated");
        return new Notification(getNotifyID, getUserID, getNotifyType, getNotifyStatus, getNotifyCreated);
    }

    @Override
    protected List<Notification> getAllNotification(Connection connection, Object object) throws SQLException {
        ArrayList<Notification> notificationAllList = new ArrayList<>();

        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_GET_ALL_NOTIFICATION);
                preparedStatement.setString(1, object.toString());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Notification notification = getInfoNotificationFromSQL(resultSet);
                    notificationAllList.add(notification);
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
        return notificationAllList;
    }

    @Override
    protected boolean addNotification(Connection connection, Notification notification) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_INSERT_NOTIFICATION);

                preparedStatement.setString(1, notification.getNotifyID());
                preparedStatement.setString(2, notification.getUserID());
                preparedStatement.setString(3, notification.getNotifyType());
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
    protected boolean updateNotification(Connection connection, Object notifyID, Object userID) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_UPDATE_NOTIFICATION);
                preparedStatement.setString(1, notifyID.toString());
                preparedStatement.setString(2, userID.toString());
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
    protected int countNotification(Connection connection, Object userID) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_GET_TOTAL_NOTIFICATION);
                preparedStatement.setString(1, userID.toString());
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
