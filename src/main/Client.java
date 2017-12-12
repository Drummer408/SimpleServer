package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private DataInputStream din;
    private DataOutputStream dout;

    public Client(String host, int port) {
        try {
            socket = new Socket(host, port);
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void sendMessage(String message) {
        try {
            dout.writeUTF(message);
            dout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() {
        String response = "";
        try {
            response = din.readUTF();
        } catch (IOException e) {
            response = e.getMessage();
        }
        return response;
    }

    public static void main(String[] args) {
        Client client = new Client(GlobalConstants.HOST, GlobalConstants.PORT);
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (input.toLowerCase() != GlobalConstants.EXIT_FLAG) {
            System.out.print("$ ");
            input = scanner.nextLine();
            client.sendMessage(input);

            String response = client.receiveMessage();
            System.out.println(response);
        }
    }
}
