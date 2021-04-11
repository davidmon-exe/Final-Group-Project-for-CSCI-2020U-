package org.openjfx;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * Server is calling ServerThread to get the boardState from a client.
 */

public class Server {
    protected Socket clientSocket = null;
    protected ServerSocket serverSocket = null;
    protected ServerThread[] threads = null; //need a new java class
    protected int numClients = 0;
    protected ArrayList board = new ArrayList();

    public static int SERVER_PORT = 11111;
    public static int MAX_CLIENTS = 2;

    public Server() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);

            threads = new ServerThread[MAX_CLIENTS];
            while(true) {
                clientSocket = serverSocket.accept();
                System.out.println("Client #"+(numClients+1)+" connected.");
                threads[numClients] = new ServerThread(clientSocket, board);
                threads[numClients].start();
                numClients++;
            }
        } catch (IOException e) {
            System.err.println("IOException while creating server connection");
        }
    }

    public static void main(String[] args) {
        System.out.println(args[0]);
        Server app = new Server();
    }
}
