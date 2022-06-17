package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.utils.DBUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractNews<T> {

    private Connection connection;

    protected abstract List<T> getNews(Connection connection, Object value, Object action) throws SQLException;

    protected abstract boolean addNews(Connection connection, T news) throws SQLException;

    protected abstract boolean updateNews(Connection connection, T news, Object object) throws SQLException;

    protected abstract int countNews(Connection connection) throws SQLException;

    protected abstract T checkNews(Connection connection, Object object) throws SQLException;

    /**
     * Get News
     *
     * @param value
     * @param action
     * @return
     * @throws SQLException
     */
    public List<T> getNews(Object value, Object action) throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getNews(connection, value, action);
        } finally {
            connection.close();
        }
        return list;
    }

    /**
     * Add News
     *
     * @param news
     * @return
     * @throws SQLException
     */
    public boolean addNews(T news) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = addNews(connection, news);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * Update News
     *
     * @param news
     * @param object
     * @return
     * @throws SQLException
     */
    public boolean updateNews(T news, Object object) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = updateNews(connection, news, object);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * Count how many news in the list
     *
     * @return
     * @throws SQLException
     */
    public int countNews() throws SQLException {
        int check;

        try {
            connection = DBUtils.makeConnection();
            check = countNews(connection);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * Check if the news exists
     *
     * @param object
     * @return
     * @throws SQLException
     */
    public T checkNews(Object object) throws SQLException {
        T t = null;

        try {
            connection = DBUtils.makeConnection();
            t = checkNews(connection, object);
        } finally {
            connection.close();
        }
        return t;
    }
}
