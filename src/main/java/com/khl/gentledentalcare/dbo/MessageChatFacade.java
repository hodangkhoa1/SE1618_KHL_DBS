package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.models.MessageChat;
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
public class MessageChatFacade extends AbstractMessageChat<MessageChat> {
    
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final String SQL_GET_ALL_MESSAGE_CHAT = "SELECT * FROM MessageChat";
    private static final String SQL_ADD_MESSAGE_CHAT = "INSERT INTO MessageChat(RoomID, EmployeeID, UserID, ChatContent) VALUES(?, ?, ?, ?)";
    
    private MessageChat getInfoMessageChatFromSQL(ResultSet resultSet) throws SQLException {
        String getRoomID = resultSet.getString("RoomID");
        String getEmployeeID = resultSet.getString("EmployeeID");
        String getUserID = resultSet.getString("UserID");
        String getChatContent = resultSet.getString("ChatContent");
        Timestamp getChatDate = resultSet.getTimestamp("ChatDate");

        return new MessageChat(getRoomID, getChatContent, getEmployeeID, getUserID, getChatDate);
    }

    @Override
    protected List<MessageChat> getAllMessageChat(Connection connection) throws SQLException {
        ArrayList<MessageChat> messageChatList = new ArrayList<>();

        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_GET_ALL_MESSAGE_CHAT);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    MessageChat messageChat = getInfoMessageChatFromSQL(resultSet);
                    messageChatList.add(messageChat);
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
        return messageChatList;
    }

    @Override
    protected boolean addMessageChat(Connection connection, MessageChat messageChat) throws SQLException {
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_ADD_MESSAGE_CHAT);
                preparedStatement.setString(1, messageChat.getRoomID());
                preparedStatement.setString(2, messageChat.getEmployeeID());
                preparedStatement.setString(3, messageChat.getUserID());
                preparedStatement.setString(4, messageChat.getChatContent());
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
