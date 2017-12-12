package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ClientThread extends Thread {
    public static final int MIN_NUM_ARGS = 2;
    private static final String EXIT_FLAG = "exit";

    private Socket socket;
    private DataInputStream din;
    private DataOutputStream dout;

    public ClientThread(Socket socket) {
        this.socket = socket;
        try {
            din = new DataInputStream(this.socket.getInputStream());
            dout = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String input = "";

        while (!input.toLowerCase().equals(EXIT_FLAG)) {
            try {
                input = din.readUTF();
                String[] inputArray = input.split(GlobalConstants.INPUT_DELIM);
                if (inputArray.length < MIN_NUM_ARGS) {
                    dout.writeUTF("You must enter a MINIMUM of three arguments.\nThe first argument must be the " +
                        "name of a math function (add, subtract, multiply, or divide).\nThe remaining arguments " +
                        "must be real numbers.\n");
                    dout.flush();
                    continue;
                }

                String mathFunction = inputArray[0];
                double[] arguments = new double[inputArray.length - 1];
                for (int i = 1; i < inputArray.length; i++) {
                    // TODO: CHECK IF STRING IS NUMERIC. IF SO, PARSE IT AND ADD TO ARGS ARRAY. IF NOT, CONTINUE THRU LOOP
                    arguments[i - 1] = Double.parseDouble(inputArray[i]);
                }

                Math math = new Math();
                double result = math.processCalculation(mathFunction, arguments);

                dout.writeUTF("Result: " + result);
                dout.flush();

            } catch (IOException | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        closeConnection();
    }

    private void closeConnection() {
        try {
            din.close();
            dout.close();
            socket.close();
            this.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
