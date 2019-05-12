package hkr.da216a.medicine.server.database;

import hkr.da216a.medicine.model.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseQueries {

    public static Connection connection;

    public Doctor getDoctor(String personalNumber, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
                "SELECT * " +
                "FROM " + Doctor.TABLE_NAME + " " +
                "WHERE " +
                Doctor.PERSONAL_NUMBER_COLUMN + " = ? " +
                "AND " +
                Doctor.PASSWORD_COLUMN + " = ? ");

        statement.setString(1, personalNumber);
        statement.setString(2, password);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            personalNumber = resultSet.getString(Doctor.PERSONAL_NUMBER_COLUMN);
            password = resultSet.getString(Doctor.PASSWORD_COLUMN);
            String name = resultSet.getString(Doctor.NAME_COLUMN);
            return new Doctor(personalNumber, password, name);
        }
        return null;
    }
}
