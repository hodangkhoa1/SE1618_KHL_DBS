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
public abstract class AbstractBooking<T> {

    private Connection connection;

    protected abstract List<T> getAllBooking(Connection connection) throws SQLException;

    protected abstract boolean addBooking(Connection connection, T booking) throws SQLException;

    protected abstract boolean updateBooking(Connection connection, Object bookingID) throws SQLException;

    protected abstract int countBooking(Connection connection) throws SQLException;

    /**
     * Get all booking
     *
     * @return
     * @throws SQLException
     */
    public List<T> getAllBooking() throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getAllBooking(connection);
        } finally {
            connection.close();
        }
        return list;
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
     * @param bookingID
     * @return
     * @throws SQLException
     */
    public boolean updateBooking(Object bookingID) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = updateBooking(connection, bookingID);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * *
     * Count how many booking in the list
     *
     * @return
     * @throws SQLException
     */
    public int countBooking() throws SQLException {
        int check;

        try {
            connection = DBUtils.makeConnection();
            check = countBooking(connection);
        } finally {
            connection.close();
        }

        return check;
    }
}
