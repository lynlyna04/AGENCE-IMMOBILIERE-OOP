package app;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {


    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "ouabel";
    private static final String PASSWORD = "lyna";

    private static Connection connection = null;

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }


}
