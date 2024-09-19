import Config.DbConnection;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            DbConnection db = DbConnection.getInstance();
            System.out.println("Database connection established.");

            // Start the console interface
        } catch (SQLException e) {
            System.err.println("Failed to establish database connection: " + e.getMessage());
        }
    }
}