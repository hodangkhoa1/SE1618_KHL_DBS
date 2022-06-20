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

    protected abstract List<T> getAllSlot(Connection connection) throws SQLException;

    /**
     * *
     * Get all slot
     *
     * @return
     * @throws SQLException
     */
    public List<T> getAllSlot() throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getAllSlot(connection);
        } finally {
            connection.close();
        }
        return list;
    }
}
