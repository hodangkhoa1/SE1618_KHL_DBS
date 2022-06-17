package com.khl.gentledentalcare.dbo;

import com.khl.gentledentalcare.utils.DBUtils;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractViewer {

    private Connection connection;

    protected abstract boolean updateViewer(Connection connection) throws SQLException;

    protected abstract int getViewer(Connection connection) throws SQLException;

    /**
     * *
     * Update Viewer
     *
     * @return
     * @throws SQLException
     */
    public boolean updateViewer() throws SQLException {
        boolean check;

        try {
            connection = DBUtils.makeConnection();
            check = updateViewer(connection);
        } finally {
            connection.close();
        }

        return check;
    }

    /**
     * *
     * Get Viewer
     *
     * @return
     * @throws SQLException
     */
    public int getViewer() throws SQLException {
        int check;

        try {
            connection = DBUtils.makeConnection();
            check = getViewer(connection);
        } finally {
            connection.close();
        }

        return check;
    }
}
