package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.utils.DBUtils;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractNewsDetail<T> {

    private Connection connection;

    protected abstract T getNewsDetail(Connection connection, Object newsDetailID) throws SQLException;

    protected abstract boolean addNewsDetail(Connection connection, T newsDetail) throws SQLException;

    protected abstract boolean updateNewsDetail(Connection connection, T newsDetail) throws SQLException;

    /**
     * Get News Detail
     *
     * @param newsDetailID
     * @return
     * @throws SQLException
     */
    public T getNewsDetail(Object newsDetailID) throws SQLException {
        T t = null;

        try {
            connection = DBUtils.makeConnection();
            t = getNewsDetail(connection, newsDetailID);
        } finally {
            connection.close();
        }
        return t;
    }

    /**
     * Add News Detail
     *
     * @param newsDetail
     * @return
     * @throws SQLException
     */
    public boolean addNewsDetail(T newsDetail) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = addNewsDetail(connection, newsDetail);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * Update News Detail
     *
     * @param newsDetail
     * @return
     * @throws SQLException
     */
    public boolean updateNewsDetail(T newsDetail) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = updateNewsDetail(connection, newsDetail);
        } finally {
            connection.close();
        }

        return check;
    }
}
