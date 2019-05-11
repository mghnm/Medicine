package hkr.da216a.medicine.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private String databaseUrl;
    private Connection connection;

    public DatabaseConnection() {
        //Syntax https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-reference-jdbc-url-format.html
        String protocol = "jdbc:mysql://";
        String host = "localhost";
        String port = ":3306";
        String databaseName = "/da216agroup15";
        String user = "?user";
        String userValue = "=root";
        String password = "&password";
        String passwordValue = "=root";
        this.databaseUrl = protocol + host + port + databaseName + user + userValue + password + passwordValue;
    }

    public Connection initializeConnection() throws SQLException {
        this.connection = DriverManager.getConnection(databaseUrl);
        return this.connection;
    }
}
