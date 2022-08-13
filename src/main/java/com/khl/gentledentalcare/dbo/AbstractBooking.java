package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.utils.DBUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ASUS
 * @param <T>
 */
public abstract class AbstractBooking<T> {

    private Connection connection;

    protected abstract List<T> getAllBooking(Connection connection, Object action, Object value, T booking) throws SQLException;

    protected abstract T getBooking(Connection connection, Object object, Object action) throws SQLException;

    protected abstract boolean addBooking(Connection connection, T booking) throws SQLException;

    protected abstract boolean updateBooking(Connection connection, T booking, Object action) throws SQLException;

    protected abstract int countBooking(Connection connection, Object status, Object action, Object userID) throws SQLException;

    protected abstract HashMap<Integer, Integer> statisticBooking(Connection connection, Object object) throws SQLException;

    /**
     * Get all booking
     *
     * @param action
     * @param value
     * @param booking
     * @return
     * @throws SQLException
     */
    public List<T> getAllBooking(Object action, Object value, T booking) throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getAllBooking(connection, action, value, booking);
        } finally {
            connection.close();
        }
        return list;
    }

    /**
     * *
     * Check if the account exists
     *
     * @param object
     * @param action
     * @return
     * @throws SQLException
     */
    public T getBooking(Object object, Object action) throws SQLException {
        T t = null;

        try {
            connection = DBUtils.makeConnection();
            t = getBooking(connection, object, action);
        } finally {
            connection.close();
        }
        return t;
    }

    /**
     * Add booking
     *
     * @param booking
     * @return
     * @throws SQLException
     */
    public boolean addBooking(T booking) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = addBooking(connection, booking);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * Update booking
     *
     * @param booking
     * @param action
     * @return
     * @throws SQLException
     */
    public boolean updateBooking(T booking, Object action) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = updateBooking(connection, booking, action);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * *
     * Count how many booking in the list
     *
     * @param status
     * @param action
     * @param userID
     * @return
     * @throws SQLException
     */
    public int countBooking(Object status, Object action, Object userID) throws SQLException {
        int check;

        try {
            connection = DBUtils.makeConnection();
            check = countBooking(connection, status, action, userID);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * *
     * Statistic Account in one month
     *
     * @param object
     * @return
     * @throws SQLException
     */
    public HashMap<Integer, Integer> statisticBooking(Object object) throws SQLException {
        HashMap<Integer, Integer> list = new HashMap<>();

        try {
            connection = DBUtils.makeConnection();
            list = statisticBooking(connection, object);
        } finally {
            connection.close();
        }
        return list;
    }
}
