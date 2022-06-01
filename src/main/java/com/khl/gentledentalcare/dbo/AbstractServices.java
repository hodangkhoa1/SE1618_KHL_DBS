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
public abstract class AbstractServices<T> {

    private Connection connection;

    protected abstract List<T> getAllServices(Connection connection) throws SQLException;

    protected abstract boolean addServices(Connection connection, T service) throws SQLException;

    protected abstract boolean updateServices(Connection connection, Object serviceID) throws SQLException;

    protected abstract int countServices(Connection connection) throws SQLException;

    /**
     * Get all services
     *
     * @return
     * @throws SQLException
     */
    public List<T> getAllServices() throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getAllServices(connection);
        } finally {
            connection.close();
        }
        return list;
    }

    /**
     * Add service
     *
     * @param service
     * @return
     * @throws SQLException
     */
    public boolean addServices(T service) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = addServices(connection, service);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * Update service
     *
     * @param serviceID
     * @return
     * @throws SQLException
     */
    public boolean updateServices(Object serviceID) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = updateServices(connection, serviceID);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * *
     * Count how many service in the list
     *
     * @return
     * @throws SQLException
     */
    public int countServices() throws SQLException {
        int check;

        try {
            connection = DBUtils.makeConnection();
            check = countServices(connection);
        } finally {
            connection.close();
        }

        return check;
    }
}
