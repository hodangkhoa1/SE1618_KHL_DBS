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
public abstract class AbstractAccount<T> {

    private Connection connection;

    protected abstract String registerAccount(Connection connection, T t) throws SQLException;

    protected abstract boolean updateAccount(Connection connection, T t, Object object) throws SQLException;

    protected abstract List<T> getAccount(Connection connection, Object object) throws SQLException;

    protected abstract T checkAccount(Connection connection, Object object) throws SQLException;

    protected abstract int countAccount(Connection connection) throws SQLException;

    /**
     * *
     * Add a new account
     *
     * @param t
     * @return
     * @throws SQLException
     */
    public String registerAccount(T t) throws SQLException {
        String tmp = "";

        try {
            connection = DBUtils.makeConnection();
            tmp = registerAccount(connection, t);
        } finally {
            connection.close();
        }
        return tmp;
    }

    /**
     * *
     * Update Information Account
     *
     * @param t
     * @param object
     * @return
     * @throws SQLException
     */
    public boolean updateAccount(T t, Object object) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = updateAccount(connection, t, object);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * *
     * Paging account in admin page
     *
     * @param object
     * @return
     * @throws SQLException
     */
    public List<T> getAccount(Object object) throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getAccount(connection, object);
        } finally {
            connection.close();
        }
        return list;
    }

    /**
     * *
     * Check if the account exists
     *
     * @param object
     * @return
     * @throws SQLException
     */
    public T checkAccount(Object object) throws SQLException {
        T t = null;

        try {
            connection = DBUtils.makeConnection();
            t = checkAccount(connection, object);
        } finally {
            connection.close();
        }
        return t;
    }

    /**
     * *
     * Count how many accounts in the list
     *
     * @return
     * @throws SQLException
     */
    public int countAccount() throws SQLException {
        int check;

        try {
            connection = DBUtils.makeConnection();
            check = countAccount(connection);
        } finally {
            connection.close();
        }

        return check;
    }
}