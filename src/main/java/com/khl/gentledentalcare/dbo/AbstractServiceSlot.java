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
public abstract class AbstractServiceSlot<T> {

    private Connection connection;

    protected abstract List<T> getAllServiceSlot(Connection connection, T serviceSlot, Object action) throws SQLException;

    protected abstract boolean addServiceSlot(Connection connection, T serviceSlot) throws SQLException;

    protected abstract boolean updateServiceSlot(Connection connection, Object serviceSlotID) throws SQLException;
    
    protected abstract T getServiceSlot(Connection connection, Object slotServiceID, Object action) throws SQLException;

    /**
     * Get all service slot
     *
     * @param serviceSlot
     * @param action
     * @return
     * @throws SQLException
     */
    public List<T> getAllServiceSlot(T serviceSlot, Object action) throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getAllServiceSlot(connection, serviceSlot, action);
        } finally {
            connection.close();
        }
        return list;
    }

    /**
     * Add service slot
     *
     * @param serviceSlot
     * @return
     * @throws SQLException
     */
    public boolean addServiceSlot(T serviceSlot) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = addServiceSlot(connection, serviceSlot);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * Update service slot
     *
     * @param serviceSlotID
     * @return
     * @throws SQLException
     */
    public boolean updateServiceSlot(Object serviceSlotID) throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = updateServiceSlot(connection, serviceSlotID);
        } finally {
            connection.close();
        }

        return check;
    }
    
    /**
     * Get services Detail
     *
     * @param slotServiceID
     * @param action
     * @return
     * @throws SQLException
     */
    public T getServiceSlot(Object slotServiceID, Object action) throws SQLException {
        T t = null;

        try {
            connection = DBUtils.makeConnection();
            t = getServiceSlot(connection, slotServiceID, action);
        } finally {
            connection.close();
        }
        return t;
    }
}
