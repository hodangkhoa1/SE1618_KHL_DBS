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
public abstract class AbstractDentist<T> {

    private Connection connection;

    protected abstract List<T> getAllDentist(Connection connection, Object object, Object action) throws SQLException;

    protected abstract boolean addDentist(Connection connection, T dentist) throws SQLException;

    protected abstract boolean updateDentist(Connection connection, T dentist) throws SQLException;

    protected abstract T getDentistDetail(Connection connection, Object dentistID) throws SQLException;

    protected abstract int countDentist(Connection connection) throws SQLException;

    /**
     * *
     * Get all dentist
     *
     * @param object
     * @param action
     * @return
     * @throws SQLException
     */
    public List<T> getAllDentist(Object object, Object action) throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getAllDentist(connection, object, action);
        } finally {
            connection.close();
        }
        return list;
    }

    /**
     * *
     * Add dentist
     *
     * @param dentist
     * @return
     * @throws SQLException
     */
    public boolean addDentist(T dentist) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = addDentist(connection, dentist);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * *
     * Update dentist
     *
     * @param dentist
     * @return
     * @throws SQLException
     */
    public boolean updateDentist(T dentist) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = updateDentist(connection, dentist);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * *
     * Count how many dentist in the list
     *
     * @return
     * @throws SQLException
     */
    public int countDentist() throws SQLException {
        int check;

        try {
            connection = DBUtils.makeConnection();
            check = countDentist(connection);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * Get dentist Detail
     *
     * @param dentistID
     * @return
     * @throws SQLException
     */
    public T getDentistDetail(Object dentistID) throws SQLException {
        T t = null;

        try {
            connection = DBUtils.makeConnection();
            t = getDentistDetail(connection, dentistID);
        } finally {
            connection.close();
        }
        return t;
    }
}
