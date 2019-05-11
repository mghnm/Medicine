import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int port = 45454;
        String server = "85.197.159.81";
        new Main(server, port);
    }

    private BufferedReader socketIn;
    private PrintWriter socketOut;

    public Main(String server, int port) {
        try { // create a socket to connect to the server
            Socket clientSocket = new Socket(server, port);
            // create data input/output streams
            socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            socketOut = new PrintWriter(clientSocket.getOutputStream(), true);

            tryMessage("login;ste;ste");
            tryMessage("login;ste;stel");
            tryMessage("login;stel;ste");
            tryMessage("login;ste;ste");
            tryMessage("disconnect");

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
        String serverResponse = socketIn.readLine();
        System.out.println("Logged in? : " + serverResponse);
        System.out.println("---");
    }
}