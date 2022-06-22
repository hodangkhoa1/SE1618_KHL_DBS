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
public abstract class AbstractHospital<T> {

    private Connection connection;

    protected abstract List<T> getAllHospital(Connection connection, Object object, Object action, Object status) throws SQLException;
    
    protected abstract T getHospital(Connection connection, Object hospitalID) throws SQLException;

    protected abstract boolean addHospital(Connection connection, T hospital) throws SQLException;

    protected abstract boolean updateHospital(Connection connection, T hospital, Object object) throws SQLException;

    protected abstract int countHospital(Connection connection) throws SQLException;

    /**
     * *
     * Get all hospital
     *
     * @param object
     * @param action
     * @param status
     * @return
     * @throws SQLException
     */
    public List<T> getAllHospital(Object object, Object action, Object status) throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getAllHospital(connection, object, action, status);
        } finally {
            connection.close();
        }
        return list;
    }
    
    /**
     * Get services Detail
     *
     * @param hospitalID
     * @return
     * @throws SQLException
     */
    public T getHospital(Object hospitalID) throws SQLException {
        T t = null;

        try {
            connection = DBUtils.makeConnection();
            t = getHospital(connection, hospitalID);
        } finally {
            connection.close();
        }
        return t;
    }

    /**
     * *
     * Add hospital
     *
     * @param hospital
     * @return
     * @throws SQLException
     */
    public boolean addHospital(T hospital) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = addHospital(connection, hospital);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * *
     * Update hospital
     *
     * @param hospital
     * @param object
     * @return
     * @throws SQLException
     */
    public boolean updateHospital(T hospital, Object object) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = updateHospital(connection, hospital, object);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * *
     * Count how many hospital in the list
     *
     * @return
     * @throws SQLException
     */
    public int countHospital() throws SQLException {
        int check;

        try {
            connection = DBUtils.makeConnection();
            check = countHospital(connection);
        } finally {
            connection.close();
        }

        return check;
    }
}
