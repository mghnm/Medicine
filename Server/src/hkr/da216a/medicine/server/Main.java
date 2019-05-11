package hkr.da216a.medicine.server;

import hkr.da216a.medicine.server.database.DatabaseConnection;
import hkr.da216a.medicine.server.database.DatabaseQueries;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Main {

    private static DatabaseConnection databaseConnection;
    private static DatabaseQueries databaseQueries;
    private static SocketConnection socketConnection;
    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        initializeDatabaseConnection();
        initializeSocketConnection();
        runServerService();
    }

    private static void initializeDatabaseConnection() {
        System.out.println("Main: Initializing database connection");
        databaseConnection = new DatabaseConnection();
        try {
            databaseQueries = new DatabaseQueries();
            DatabaseQueries.connection = databaseConnection.initializeConnection();
            System.out.println("Main: Connection to database complete");
        } catch (SQLException sqlException) {
            System.out.println("Main: Connection to localhost failed for some reason. Stack trace:");
            sqlException.printStackTrace();
            System.exit(-1);
        }
    }

    private static void initializeSocketConnection() {
        System.out.println("Main: Initializing socket connection");
        socketConnection = new SocketConnection();
        try {
            serverSocket = socketConnection.initializeConnection();
            System.out.println("Main: Connection to socket complete");
        } catch (IOException ioException) {
            System.out.println("Main: Connection to ServerSocket with port 45454 failed for some reason. Stack trace:");
            ioException.printStackTrace();
            System.exit(-2);
        }
    }

    private static void runServerService() {
        System.out.println("Main: Server service begins");
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept(); //Blocking operation, waiting till connection
                Thread clientThread = new Thread(new HandleClient(clientSocket));
                clientThread.start();
                System.out.println("Main: Now handling new user");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Main: Something unexpected went wrong");
            }
        }
    }
}
