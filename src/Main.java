import Config.DbConnection;
import Ui.MainMenu;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            MainMenu mainMenu = new MainMenu();
            mainMenu.mainMenu();
            DbConnection = DbConnection.getInstance();
            System.out.println("Database connection established.");

        } catch (SQLException e) {
            System.err.println("Failed to establish database connection: " + e.getMessage());
        }

    }
}