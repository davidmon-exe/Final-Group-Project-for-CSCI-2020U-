package org.openjfx;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ServerClient {
    private Socket socket = null;

    public  static String SERVER_ADDRESS = "localhost";
    public  static int    SERVER_PORT = 11111;

    /**
     * Sets server address and port
     * Takes the ArrayList sent and sets board to that new ArrayList
     */
    public ServerClient() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            List<List> board = new ArrayList<List>();
            ObjectInputStream oi = new ObjectInputStream(socket.getInputStream());
            try {
                Object object = oi.readObject();
                board = (List<List>) object;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: "+SERVER_ADDRESS);
        } catch (IOException e) {
            System.err.println("IOException while connecting to server: "+SERVER_ADDRESS);
        }
        if (socket == null) {
            System.err.println("socket is null");
        }

    }

}
