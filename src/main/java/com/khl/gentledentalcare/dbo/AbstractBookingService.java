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

    protected abstract List<T> getAllBookingService(Connection connection, T bookingService, Object action) throws SQLException;

    protected abstract boolean addBookingService(Connection connection, T bookingService) throws SQLException;

    /**
     * Get all booking service
     *
     * @param bookingService
     * @param action
     * @return
     * @throws SQLException
     */
    public List<T> getAllBookingService(T bookingService, Object action) throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getAllBookingService(connection, bookingService, action);
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
}
