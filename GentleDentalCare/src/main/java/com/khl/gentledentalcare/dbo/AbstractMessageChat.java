package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.utils.DBUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 * @param <T>
 */
public abstract class AbstractMessageChat<T> {

    private Connection connection;

    protected abstract List<T> getAllMessageChat(Connection connection) throws SQLException;

    protected abstract boolean addMessageChat(Connection connection, T messageChat) throws SQLException;
    
    /**
     * *
     * Get all message chat
     *
     * @return
     * @throws SQLException
     */
    public List<T> getAllMessageChat() throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getAllMessageChat(connection);
        } finally {
            connection.close();
        }
        return list;
    }
    
    /**
     * *
     * Add message chat
     *
     * @param messageChat
     * @return
     * @throws SQLException
     */
    public boolean addMessageChat(T messageChat) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = addMessageChat(connection, messageChat);
        } finally {
            connection.close();
        }

        return check;
    }
}
