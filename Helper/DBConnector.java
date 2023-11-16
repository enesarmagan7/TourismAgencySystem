package TourismAgencySystem.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private Connection connect = null;

    public DBConnector() {
    }

    public Connection connectDB() {
        try {
            this.connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", Config.DB_USERNAME, Config.PASSWORD);
        } catch (SQLException var2) {
            throw new RuntimeException(var2);
        }

        return this.connect;
    }

    public static Connection getInstace() {
        DBConnector dbConnecton = new DBConnector();
        return dbConnecton.connectDB();
    }
}
