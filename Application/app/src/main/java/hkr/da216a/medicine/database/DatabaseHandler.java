package hkr.da216a.medicine.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import hkr.da216a.medicine.model.Doctor;

import static hkr.da216a.medicine.database.ExceptionPrints.printClassNotFoundException;
import static hkr.da216a.medicine.database.ExceptionPrints.printIOException;

public class DatabaseHandler {

    private static DatabaseHandler ourInstance;
    private Socket clientSocket;
    private BufferedReader socketInBufferedReader;
    private ObjectInputStream socketInObjectInputStream;
    private PrintWriter socketOutPrintWriter;
    private String server = "85.197.159.81";
    private int port = 45454;

    private final String SPLIT = ";";

    private DatabaseHandler() {
    }

    static DatabaseHandler getInstance() {
        if (ourInstance == null) {
            ourInstance = new DatabaseHandler();
        }
        //TODO Here could have a check on if the streams/socket are still alive, otherwise prepareConnection again
        return ourInstance;
    }

    void prepareConnection() {
        try {
            clientSocket = new Socket(server, port);
            socketInBufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            socketInObjectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            socketOutPrintWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException ioException) {
            printIOException(ioException);
        }
    }

    void finishConnection() {
        try {
            socketOutPrintWriter.println("disconnect");
            socketInBufferedReader.close();
            socketOutPrintWriter.close();
            clientSocket.close();
        } catch (IOException ioException) {
            printIOException(ioException);
        }
    }

    Doctor getDoctor(String personalNumber, String password) {
        try {
            socketOutPrintWriter.println("getDoctor" + SPLIT + personalNumber + SPLIT + password);
            return (Doctor) socketInObjectInputStream.readObject();
        } catch (ClassNotFoundException classNotFoundException) {
            printClassNotFoundException(classNotFoundException);
        } catch (IOException ioException) {
            printIOException(ioException);
        }
        return null;
    }

}
