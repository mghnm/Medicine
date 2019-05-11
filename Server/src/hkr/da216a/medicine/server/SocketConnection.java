package hkr.da216a.medicine.server;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketConnection {

    private int socketPort;
    private ServerSocket serverSocket;

    public SocketConnection() {
        this.socketPort = 45454;
    }

    public ServerSocket initializeConnection() throws IOException {
        this.serverSocket = new ServerSocket(socketPort);
        return this.serverSocket;
    }
}
