package hkr.da216a.medicine.server.database;

import java.io.IOException;
import java.sql.SQLException;

public abstract class ExceptionPrints {

    public static void printSqlError(SQLException sqlException) {
        System.out.println("Error in sql connection");
        sqlException.printStackTrace();
    }

    public static void printIOException(IOException ioException) {
        System.out.println("Error in object output stream");
        ioException.printStackTrace();
    }

    public static void printArrayOutOfBoundsException(ArrayIndexOutOfBoundsException arrayOutOfBoundsException) {
        System.out.println("Error in message from client. Array out of bounds");
        arrayOutOfBoundsException.printStackTrace();
    }
}
