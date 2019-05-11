package hkr.da216a.medicine.server;

import hkr.da216a.medicine.model.Doctor;
import hkr.da216a.medicine.server.database.DatabaseQueries;
import hkr.da216a.medicine.server.database.ExceptionPrints;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class HandleClient implements Runnable {

    private final String SPLIT = ";";
    private DatabaseQueries databaseQueries;
    private Socket clientSocket;

    private volatile boolean running = true;

    public HandleClient(Socket clientSocket) {
        this.clientSocket = clientSocket;
        databaseQueries = new DatabaseQueries();
    }

    private BufferedReader bufferedReaderIn;
    private PrintWriter printWriterOut;
    private ObjectOutputStream objectOutputStreamOut;

    @Override
    public void run() {
        try {
            this.bufferedReaderIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.printWriterOut = new PrintWriter(clientSocket.getOutputStream(), true);
            this.objectOutputStreamOut = new ObjectOutputStream(clientSocket.getOutputStream());
            while (running) { //TODO disconnect automatically as well after some time
                System.out.println("Starting to wait for input on thread: " + Thread.currentThread().getName());
                String clientMessage = bufferedReaderIn.readLine();
                handleRequest(clientMessage);
            }
            System.out.println("Dropping client on thread: " + Thread.currentThread().getName());
            //objectOutputStreamOut.flush();
            objectOutputStreamOut.close();
            bufferedReaderIn.close();
            printWriterOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleRequest(String clientMessage) {
        System.out.println("Handling request message: " + clientMessage);

        if (clientMessage == null) {
            System.out.println("Null request requested. Proceed to discard request.");
            running = false;
            return;
        }

        String[] clientMessageArray = clientMessage.split(SPLIT);

        switch (clientMessageArray[0]) {
            case "disconnect": {
                this.running = false;
                break;
            }
            case "getDoctor": {
                getDoctor(clientMessageArray);
                break;
            }
            default: {
                break;
            }
        }
    }

    /**
     * @param message [0] "getDoctor"
     *                [1] username string
     *                [2] password string
     */
    private void getDoctor(String... message) {
        try {
            Doctor databaseResponse = databaseQueries.getDoctor(message[1], message[2]);
            objectOutputStreamOut.writeObject(databaseResponse);
        } catch (SQLException sqlException) {
            ExceptionPrints.printSqlError(sqlException);
        } catch (IOException ioException) {
            ExceptionPrints.printIOException(ioException);
        } catch (ArrayIndexOutOfBoundsException arrayOutOfBoundsException) {
            ExceptionPrints.printArrayOutOfBoundsException(arrayOutOfBoundsException);
        }
    }


}
