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
public abstract class AbstractEmployee<T> {

    private Connection connection;

    protected abstract List<T> getAllEmployee(Connection connection, Object object, Object action) throws SQLException;

    protected abstract boolean addEmployee(Connection connection, T employee) throws SQLException;

    protected abstract boolean updateEmployee(Connection connection, T employee) throws SQLException;

    protected abstract int countEmployee(Connection connection) throws SQLException;
    
    protected abstract T getEmployee(Connection connection, Object employeeID) throws SQLException;
    
    /**
     * *
     * Get all employee
     *
     * @param object
     * @param action
     * @return
     * @throws SQLException
     */
    public List<T> getAllEmployee(Object object, Object action) throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getAllEmployee(connection, object, action);
        } finally {
            connection.close();
        }
        return list;
    }
    
    /**
     * *
     * Add employee
     *
     * @param employee
     * @return
     * @throws SQLException
     */
    public boolean addEmployee(T employee) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = addEmployee(connection, employee);
        } finally {
            connection.close();
        }

        return check;
    }
    
    /**
     * *
     * Update employee
     *
     * @param employee
     * @return
     * @throws SQLException
     */
    public boolean updateEmployee(T employee) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = updateEmployee(connection, employee);
        } finally {
            connection.close();
        }

        return check;
    }
    
    /**
     * *
     * Count how many employee in the list
     *
     * @return
     * @throws SQLException
     */
    public int countEmployee() throws SQLException {
        int check;

        try {
            connection = DBUtils.makeConnection();
            check = countEmployee(connection);
        } finally {
            connection.close();
        }

        return check;
    }
    
    /**
     * Get dentist Detail
     *
     * @param employeeID
     * @return
     * @throws SQLException
     */
    public T getEmployee(Object employeeID) throws SQLException {
        T t = null;

        try {
            connection = DBUtils.makeConnection();
            t = getEmployee(connection, employeeID);
        } finally {
            connection.close();
        }
        return t;
    }
}
