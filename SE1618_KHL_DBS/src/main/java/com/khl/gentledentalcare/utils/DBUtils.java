package com.khl.gentledentalcare.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class DBUtils {

    public static Connection makeConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String urlDataConnection = "jdbc:sqlserver://localhost;databaseName=GentalCareDentals";

            Connection dataConn = DriverManager.getConnection(urlDataConnection, "sa", Encrypt.decrypt("u+X+On2XQDymkX7xtpuohQ==", "ssshhhhhhhhhhh!!!!"));

            return dataConn;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
