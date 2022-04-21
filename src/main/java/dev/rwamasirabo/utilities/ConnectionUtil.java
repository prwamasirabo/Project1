package dev.rwamasirabo.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection getConnection()
    {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://rwamasirabo-db.cv1tp2j6wufq.us-east-2.rds.amazonaws.com/project1?user=postgres&password=Widetechchf01!");
            return conn;
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

    /**
     * Test Connection
     */
    public static void main(String[] args) {
        Connection connection = ConnectionUtil.getConnection();
    }

}
