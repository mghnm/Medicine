package hkr.da216a.servertesting;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private BufferedReader socketIn;
    private PrintWriter socketOut;

    private MainActivity activity;

    public Client(MainActivity activity) {
        this.activity = activity;
        int port = 45454;
        String server = "85.197.159.81";
        runCode(server, port);
    }

    public void runCode(String server, int port) {
        try { // create a socket to connect to the server
            Socket clientSocket = new Socket(server, port);
            // create data input/output streams
            socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            socketOut = new PrintWriter(clientSocket.getOutputStream(), true);

            tryMessage("login;ste;ste");
            disconnect();

            socketIn.close();
            socketOut.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void tryMessage(String command) throws IOException {
        System.out.println("---");
        System.out.println("Executing command: " + command);
        socketOut.println(command);
        final String serverResponse = socketIn.readLine();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.setTextViewText(serverResponse);
            }
        });
        System.out.println("Logged in? : " + serverResponse);
        System.out.println("---");
    }

    private void disconnect() {
        socketOut.println("disconnect");
    }
}
