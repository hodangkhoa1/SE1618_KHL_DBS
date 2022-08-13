package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.models.RoomChat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class RoomChatFacade extends AbstractRoomChat<RoomChat> {

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final String SQL_GET_ALL_ROOM_CHAT = "SELECT * FROM RoomChat";
    private static final String SQL_ADD_ROOM_CHAT = "INSERT INTO RoomChat(RoomID, UserID) VALUES(?, ?)";
    private static final String SQL_UPDATE_STATUS_ROOM_CHAT = "UPDATE RoomChat SET RoomStatus = ? WHERE RoomID = ?";
    private static final String SQL_UPDATE_EMPLOYEE_ROOM_CHAT = "UPDATE RoomChat SET EmployeeID = ? WHERE RoomID = ?";
    private static final String SQL_GET_TOTAL_ROOM_CHAT = "SELECT COUNT(*) FROM RoomChat";

    private RoomChat getInfoRoomChatFromSQL(ResultSet resultSet) throws SQLException {
        String getRoomID = resultSet.getString("RoomID");
        String getEmployeeID = resultSet.getString("EmployeeID");
        String getUserID = resultSet.getString("UserID");
        int getRoomStatus = resultSet.getInt("RoomStatus");

        return new RoomChat(getRoomID, getEmployeeID, getUserID, getRoomStatus);
    }

    @Override
    protected List<RoomChat> getAllRoomChat(Connection connection) throws SQLException {
        ArrayList<RoomChat> roomChatList = new ArrayList<>();

        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_GET_ALL_ROOM_CHAT);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    RoomChat roomChat = getInfoRoomChatFromSQL(resultSet);
                    roomChatList.add(roomChat);
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
        return roomChatList;
    }

    @Override
    protected boolean addRoomChat(Connection connection, RoomChat roomChat) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_ADD_ROOM_CHAT);
                preparedStatement.setString(1, roomChat.getRoomID());
                preparedStatement.setString(2, roomChat.getUserID());
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
    protected boolean updateRoomChat(Connection connection, RoomChat roomChat, Object action) throws SQLException {
        try {
            if (connection != null) {
                switch (action.toString()) {
                    case "UpdateEmployeeRoomChat":
                        preparedStatement = connection.prepareStatement(SQL_UPDATE_EMPLOYEE_ROOM_CHAT);
                        preparedStatement.setString(1, roomChat.getEmployeeID());
                        preparedStatement.setString(2, roomChat.getRoomID());
                        break;
                    case "UpdateStatusRoomChat":
                        preparedStatement = connection.prepareStatement(SQL_UPDATE_STATUS_ROOM_CHAT);
                        preparedStatement.setInt(1, roomChat.getRoomStatus());
                        preparedStatement.setString(2, roomChat.getRoomID());
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
    protected int countRoomChat(Connection connection) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_GET_TOTAL_ROOM_CHAT);
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
