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
public abstract class AbstractRoomChat<T> {

    private Connection connection;

    protected abstract List<T> getAllRoomChat(Connection connection) throws SQLException;

    protected abstract boolean addRoomChat(Connection connection, T roomChat) throws SQLException;

    protected abstract boolean updateRoomChat(Connection connection, Object roomID) throws SQLException;

    protected abstract int countRoomChat(Connection connection) throws SQLException;
    
    /**
     * *
     * Get all room chat
     *
     * @return
     * @throws SQLException
     */
    public List<T> getAllRoomChat() throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getAllRoomChat(connection);
        } finally {
            connection.close();
        }
        return list;
    }
    
    /**
     * *
     * Add room chat
     *
     * @param roomChat
     * @return
     * @throws SQLException
     */
    public boolean addRoomChat(T roomChat) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = addRoomChat(connection, roomChat);
        } finally {
            connection.close();
        }

        return check;
    }
    
    /**
     * *
     * Update room chat
     *
     * @param roomID
     * @return
     * @throws SQLException
     */
    public boolean updateRoomChat(Object roomID) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = updateRoomChat(connection, roomID);
        } finally {
            connection.close();
        }

        return check;
    }
    
    /**
     * *
     * Count how many room chat in the list
     *
     * @return
     * @throws SQLException
     */
    public int countRoomChat() throws SQLException {
        int check;

        try {
            connection = DBUtils.makeConnection();
            check = countRoomChat(connection);
        } finally {
            connection.close();
        }

        return check;
    }
}
