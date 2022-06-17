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
public abstract class AbstractServiceType<T> {

    private Connection connection;

    protected abstract List<T> getAllServiceType(Connection connection) throws SQLException;

    /**
     * *
     * Get all service type
     *
     * @return
     * @throws SQLException
     */
    public List<T> getAllServiceType() throws SQLException {

        List<T> list = new ArrayList<>();

        try {
            connection = DBUtils.makeConnection();
            list = getAllServiceType(connection);
        } finally {
            connection.close();
        }
        return list;
    }
}
