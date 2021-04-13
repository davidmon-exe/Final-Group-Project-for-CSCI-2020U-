package org.openjfx;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Server is calling ServerThread to get the boardState from a client.
 */

public class Server {
    protected Socket client1 = null;
    protected Socket client2 = null;
    protected ServerSocket serverSocket = null;
    protected ServerThread[] threads = null; //need a new java class
    protected int numClients = 0;
    protected ArrayList board = new ArrayList();

    public static int SERVER_PORT = 11111;
    public static int MAX_CLIENTS = 2;

    public BufferedReader clientInput1=null, clientInput2=null;
    public BufferedWriter clientOutput1=null,clientOutput2=null;

    public Server() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            while(true) {
                // connecting player 1
                client1 = serverSocket.accept();
                clientInput1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
                clientOutput1= new BufferedWriter(new OutputStreamWriter(client1.getOutputStream()));

                String input = clientInput1.readLine();
                if(input.startsWith("CONNECT ")){
                    System.out.println("Client Connected");
                    clientOutput1.write("100\n");
                    clientOutput1.flush();

                    String firstPlayer = input.substring(8);
                    System.out.println(firstPlayer+" connected");
                    // connecting player 2
                    client2 = serverSocket.accept();
                    clientInput2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
                    clientOutput2 = new BufferedWriter(new OutputStreamWriter(client2.getOutputStream()));

                    input = clientInput2.readLine();
                    String secondPlayer = input.substring(8);
                    System.out.println(secondPlayer+" connected");

                    clientOutput1.write("200 "+secondPlayer+"\n");
                    clientOutput1.flush();
                    clientOutput2.write("200 "+firstPlayer+"\n");
                    clientOutput2.flush();

                    //pair clients
                    List<List> board  = new ArrayList<>();
                    //give boardStateArray lists
                    List<Integer> temp1 = Arrays.asList(1, 0, 0, 0, 0, 0);
                    List<Integer> temp2 = Arrays.asList(2, 0, 0, 0, 0, 0);
                    List<Integer> temp3 = Arrays.asList(1, 0, 0, 0, 0, 0);
                    List<Integer> temp4 = Arrays.asList(2, 0, 0, 0, 0, 0);
                    List<Integer> temp5 = Arrays.asList(1, 0, 0, 0, 0, 0);
                    List<Integer> temp6 = Arrays.asList(2, 0, 0, 0, 0, 0);
                    List<Integer> temp7 = Arrays.asList(1, 0, 0, 0, 0, 0);

                    board.add(temp1);
                    board.add(temp2);
                    board.add(temp3);
                    board.add(temp4);
                    board.add(temp5);
                    board.add(temp6);
                    board.add(temp7);
                    System.out.println(board);

                    clientOutput1.write(board.toString());
                    clientOutput1.flush();
                    clientOutput2.write(board.toString());
                    clientOutput2.flush();

                    ServerThread handler = new ServerThread(client1, client2, (ArrayList) board);
                    Thread thread = new Thread(handler);
                    thread.start();
                }

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
