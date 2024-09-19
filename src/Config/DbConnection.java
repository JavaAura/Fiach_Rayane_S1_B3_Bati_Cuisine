package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection instance;
    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/Chefs_House";
    private String username = "postgres";
    private String password = "password";

    private DbConnection() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new SQLException("Failed to create the database connection.", e);
        }
    }

    public static DbConnection getInstance() throws SQLException {
        if (instance == null) {
            synchronized (DbConnection.class) {
                if (instance == null) {
                    instance = new DbConnection();
                }
            }
        } else if (instance.getConnection().isClosed()) {
            instance = new DbConnection();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}