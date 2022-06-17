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
public abstract class AbstractFeedBack<T> {

    private Connection connection;

    protected abstract List<T> getAllFeedBack(Connection connection) throws SQLException;

    protected abstract boolean addFeedBack(Connection connection, T feedBack) throws SQLException;

    protected abstract boolean updateFeedBack(Connection connection, Object feedBackID) throws SQLException;

    protected abstract int countFeedBack(Connection connection) throws SQLException;

    /**
     * Get all feedBack
     *
     * @return
     * @throws SQLException
     */
    public List<T> getAllFeedBack() throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getAllFeedBack(connection);
        } finally {
            connection.close();
        }
        return list;
    }

    /**
     * Add feedBack
     *
     * @param feedBack
     * @return
     * @throws SQLException
     */
    public boolean addFeedBack(T feedBack) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = addFeedBack(connection, feedBack);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * Update feedBack
     *
     * @param feedBackID
     * @return
     * @throws SQLException
     */
    public boolean updateFeedBack(Object feedBackID) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = updateFeedBack(connection, feedBackID);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * *
     * Count how many feedBack in the list
     *
     * @return
     * @throws SQLException
     */
    public int countFeedBack() throws SQLException {
        int check;

        try {
            connection = DBUtils.makeConnection();
            check = countFeedBack(connection);
        } finally {
            connection.close();
        }

        return check;
    }
}
