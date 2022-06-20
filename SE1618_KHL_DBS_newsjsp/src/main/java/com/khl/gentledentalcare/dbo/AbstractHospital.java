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

    protected abstract List<T> getAllHospital(Connection connection) throws SQLException;

    protected abstract boolean addHospital(Connection connection, T hospital) throws SQLException;

    protected abstract boolean updateHospital(Connection connection, Object hospitalID) throws SQLException;

    protected abstract int countHospital(Connection connection) throws SQLException;

    /**
     * *
     * Get all hospital
     *
     * @return
     * @throws SQLException
     */
    public List<T> getAllHospital() throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getAllHospital(connection);
        } finally {
            connection.close();
        }
        return list;
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
     * @param hospitalID
     * @return
     * @throws SQLException
     */
    public boolean updateHospital(Object hospitalID) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = updateHospital(connection, hospitalID);
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
