package dev.rwamasirabo.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection getConnection() {
        try {

            String connectionString = System.getenv("DB_CONNECTION");
            Connection conn = DriverManager.getConnection(connectionString);
            return conn;
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
}
