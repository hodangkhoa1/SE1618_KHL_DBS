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
    
    protected abstract boolean addAccount(Connection connection, T t, Object action) throws SQLException;

    protected abstract boolean updateAccount(Connection connection, T t, Object object) throws SQLException;

    protected abstract List<T> getAccount(Connection connection, Object object, Object action, Object status) throws SQLException;

    protected abstract T checkAccount(Connection connection, T account, Object action) throws SQLException;

    protected abstract int countAccount(Connection connection, Object role) throws SQLException;

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
     * Add Information Account
     *
     * @param t
     * @param action
     * @return
     * @throws SQLException
     */
    public boolean addAccount(T t, Object action) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = addAccount(connection, t, action);
        } finally {
            connection.close();
        }

        return check;
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
     * @param action
     * @param status
     * @return
     * @throws SQLException
     */
    public List<T> getAccount(Object object, Object action, Object status) throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getAccount(connection, object, action, status);
        } finally {
            connection.close();
        }
        return list;
    }

    /**
     * *
     * Check if the account exists
     *
     * @param account
     * @param action
     * @return
     * @throws SQLException
     */
    public T checkAccount(T account, Object action) throws SQLException {
        T t = null;

        try {
            connection = DBUtils.makeConnection();
            t = checkAccount(connection, account, action);
        } finally {
            connection.close();
        }
        return t;
    }

    /**
     * *
     * Count how many accounts in the list
     *
     * @param role
     * @return
     * @throws SQLException
     */
    public int countAccount(Object role) throws SQLException {
        int check;

        try {
            connection = DBUtils.makeConnection();
            check = countAccount(connection, role);
        } finally {
            connection.close();
        }

        return check;
    }
}
