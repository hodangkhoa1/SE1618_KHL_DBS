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

    protected abstract List<T> getServices(Connection connection, Object value, Object action) throws SQLException;

    protected abstract T getServicesDetail(Connection connection, Object serviceID) throws SQLException;

    protected abstract boolean addServices(Connection connection, T service) throws SQLException;

    protected abstract boolean updateServices(Connection connection, T serviceID, Object object) throws SQLException;

    protected abstract int countServices(Connection connection) throws SQLException;

    /**
     * Get all services
     *
     * @param value
     * @param action
     * @return
     * @throws SQLException
     */
    public List<T> getServices(Object value, Object action) throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getServices(connection, value, action);
        } finally {
            connection.close();
        }
        return list;
    }

    /**
     * Get services Detail
     *
     * @param serviceID
     * @return
     * @throws SQLException
     */
    public T getServicesDetail(Object serviceID) throws SQLException {
        T t = null;

        try {
            connection = DBUtils.makeConnection();
            t = getServicesDetail(connection, serviceID);
        } finally {
            connection.close();
        }
        return t;
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
     * @param service
     * @param object
     * @return
     * @throws SQLException
     */
    public boolean updateServices(T service, Object object) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = updateServices(connection, service, object);
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
