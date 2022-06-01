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

    protected abstract List<T> getAllEmployee(Connection connection) throws SQLException;

    protected abstract boolean addEmployee(Connection connection, T employee) throws SQLException;

    protected abstract boolean updateEmployee(Connection connection, Object employeeID) throws SQLException;

    protected abstract int countEmployee(Connection connection) throws SQLException;
    
    /**
     * *
     * Get all employee
     *
     * @return
     * @throws SQLException
     */
    public List<T> getAllEmployee() throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getAllEmployee(connection);
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
     * @param employeeID
     * @return
     * @throws SQLException
     */
    public boolean updateEmployee(Object employeeID) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = updateEmployee(connection, employeeID);
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
}
