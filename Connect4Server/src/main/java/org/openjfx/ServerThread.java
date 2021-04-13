package org.openjfx;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * ServerThread is getting the board state for the Server
 */

public class ServerThread implements Runnable {
    protected Socket send = null , receive = null;
    protected ArrayList board = null;

    public ServerThread(Socket client1, Socket client2, ArrayList board) {
        this.send = client1;
        this.receive = client2;
        this.board = board;
    }

    public void run() {
        BufferedWriter toSender = null;
        BufferedWriter toReceiver = null;
        BufferedReader clientInput = null;
        Socket temp = null;
        String input = "";

        try {
            while(true){
                //get output to sender and receiver
                toSender = new BufferedWriter(new OutputStreamWriter(send.getOutputStream()));
                toReceiver = new BufferedWriter(new OutputStreamWriter(receive.getOutputStream()));
                clientInput = new BufferedReader(new InputStreamReader(send.getInputStream()));

                //checkWin
                /* if red(1) wins send player 1 wins
                *  else if yellow(2) wins send player 2 wins
                *  else send new board state to both players
                */

                //sending board to both players
                //System.out.println("Updated Board");
                toSender.write("0\n");
                toSender.flush();
                toSender.write(board.toString());
                toSender.flush();
                toReceiver.write("0\n");
                toReceiver.flush();
                toReceiver.write(board.toString());
                toReceiver.flush();
                temp = send;
                send = receive;
                receive = temp;

            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
