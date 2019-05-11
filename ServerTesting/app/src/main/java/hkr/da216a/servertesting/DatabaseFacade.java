package hkr.da216a.servertesting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DatabaseFacade {

    private static DatabaseFacade ourInstance;
    private BufferedReader socketIn;
    private Socket clientSocket;
    private PrintWriter socketOut;
    private String server = "85.197.159.81";
    private int port = 45454;

    private DatabaseFacade() {
        prepareConnection();
    }

    public static DatabaseFacade getInstance() {
        if (ourInstance == null) {
            ourInstance = new DatabaseFacade();
        }
        return ourInstance;
    }

    private void prepareConnection() {
        try {
            clientSocket = new Socket(server, port);
            socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void finishConnection() {
        try {
            socketOut.println("disconnect");
            socketIn.close();
            socketOut.close();
            clientSocket.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void loginUser(final MainActivity mainActivity, final String username, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socketOut.println("login;" + username + ";" + password);
                    final String serverResponse = socketIn.readLine();
                    mainActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mainActivity.loginUser(serverResponse);
                        }
                    });
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }).start();
    }

    public Doctor getDoctor(String username, String password) {

    }
}
