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
public abstract class AbstractSlot<T> {

    private Connection connection;

    protected abstract List<T> getAllSlot(Connection connection, Object slotID, Object action) throws SQLException;

    protected abstract T getSlot(Connection connection, Object slotID, Object action) throws SQLException;

    /**
     * *
     * Get all slot
     *
     * @param slotID
     * @param action
     * @return
     * @throws SQLException
     */
    public List<T> getAllSlot(Object slotID, Object action) throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getAllSlot(connection, slotID, action);
        } finally {
            connection.close();
        }
        return list;
    }

    /**
     * Get services Detail
     *
     * @param slotID
     * @param action
     * @return
     * @throws SQLException
     */
    public T getSlot(Object slotID, Object action) throws SQLException {
        T t = null;

        try {
            connection = DBUtils.makeConnection();
            t = getSlot(connection, slotID, action);
        } finally {
            connection.close();
        }
        return t;
    }
}
