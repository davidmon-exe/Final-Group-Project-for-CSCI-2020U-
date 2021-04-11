package org.openjfx;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * ServerThread is getting the board state for the Server
 */

public class ServerThread extends Thread {
    protected Socket socket = null;
//    protected PrintWriter out = null;
//    protected BufferedReader in = null;
    protected ArrayList board = null;

    public ServerThread(Socket socket, ArrayList board) {
        super();
        this.socket = socket;
        this.board = board;
//        try {
//            out = new PrintWriter(socket.getOutputStream(), true);
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        } catch (IOException e) {
//            System.err.println("IOException while opening a read/write connection");
//        }
    }

    public void run() {
        // initialize interaction
//        out.println("Connected to Server");
//        out.println("Ready");

//        boolean endOfSession = false;
//        while(!endOfSession) {
//            endOfSession = processCommand();
//        }
        try {
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
