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

    protected abstract List<T> getAllFeedBack(Connection connection, Object object, Object action) throws SQLException;

    protected abstract T getFeedBack(Connection connection, Object object, Object userID) throws SQLException;

    protected abstract boolean addFeedBack(Connection connection, T feedBack) throws SQLException;

    protected abstract int countFeedBack(Connection connection) throws SQLException;

    /**
     * Get all feedBack
     *
     * @param object
     * @param action
     * @return
     * @throws SQLException
     */
    public List<T> getAllFeedBack(Object object, Object action) throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getAllFeedBack(connection, object, action);
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

    /**
     * *
     * Get FeedBack
     *
     * @param object
     * @param userID
     * @return
     * @throws SQLException
     */
    public T getFeedBack(Object object, Object userID) throws SQLException {
        T t = null;

        try {
            connection = DBUtils.makeConnection();
            t = getFeedBack(connection, object, userID);
        } finally {
            connection.close();
        }
        return t;
    }
}
