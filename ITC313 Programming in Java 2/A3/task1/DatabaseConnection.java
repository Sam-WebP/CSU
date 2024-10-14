package task1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides a connection to the TaxManagementSystem database.
 */
public class DatabaseConnection {

    /**
     * Establishes a connection to the MySQL database.
     *
     * @return A Connection object if successful, null otherwise.
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/TaxManagementSystem";
            String username = "your_mysql_username";
            String password = "your_mysql_password";

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console.");
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * Tests the database connection.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        getConnection();
    }
}
