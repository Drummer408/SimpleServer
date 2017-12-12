package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private ServerSocket serverSocket;

    public Server(int port) {
        this.port = port;
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server listening for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client has joined!");
                ClientThread clientThread = new ClientThread(clientSocket);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Server server = new Server(GlobalConstants.PORT);
        server.run();
    }
}
