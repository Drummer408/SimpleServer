package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread {
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

        while (!input.toLowerCase().equals(GlobalConstants.EXIT_FLAG)) {
            try {
                input = din.readUTF();
                String[] inputArray = input.split(GlobalConstants.INPUT_DELIM);
                String mathFunction = inputArray[0];
                if (inputArray.length < GlobalConstants.MIN_NUM_ARGS || !Math.availableFunctions.contains(mathFunction)) {
                    dout.writeUTF("You must enter a minimum of three arguments.\nThe first argument must be the " +
                        "name of a math function (add, subtract, multiply, or divide).\nThe remaining arguments " +
                        "must be real numbers.\n");
                    dout.flush();
                    continue;
                }

                double[] arguments = new double[inputArray.length - 1];
                for (int i = 1; i < inputArray.length; i++) {
                    // TODO: CHECK IF STRING IS NUMERIC. IF SO, PARSE IT AND ADD TO ARGS ARRAY. IF NOT, CONTINUE THRU LOOP
                    arguments[i - 1] = Double.parseDouble(inputArray[i]);
                }

                Math math = new Math();
                double result = math.processCalculation(mathFunction, arguments);

                dout.writeUTF("Result: " + result + "\n");
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
