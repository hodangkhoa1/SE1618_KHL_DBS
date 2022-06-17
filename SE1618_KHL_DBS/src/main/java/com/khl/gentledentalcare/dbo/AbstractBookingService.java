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
public abstract class AbstractBookingService<T> {

    private Connection connection;

    protected abstract List<T> getAllBookingService(Connection connection) throws SQLException;

    protected abstract boolean addBookingService(Connection connection, T bookingService) throws SQLException;

    protected abstract boolean updateBookingService(Connection connection, Object bookingServiceID) throws SQLException;

    protected abstract int countBookingService(Connection connection) throws SQLException;

    /**
     * Get all booking service
     *
     * @return
     * @throws SQLException
     */
    public List<T> getAllBookingService() throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getAllBookingService(connection);
        } finally {
            connection.close();
        }
        return list;
    }

    /**
     * Add booking service
     *
     * @param bookingService
     * @return
     * @throws SQLException
     */
    public boolean addBookingService(T bookingService) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = addBookingService(connection, bookingService);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * Update booking service
     *
     * @param bookingServiceID
     * @return
     * @throws SQLException
     */
    public boolean updateBookingService(Object bookingServiceID) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = updateBookingService(connection, bookingServiceID);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * *
     * Count how many booking service in the list
     *
     * @return
     * @throws SQLException
     */
    public int countBookingService() throws SQLException {
        int check;

        try {
            connection = DBUtils.makeConnection();
            check = countBookingService(connection);
        } finally {
            connection.close();
        }

        return check;
    }
}
