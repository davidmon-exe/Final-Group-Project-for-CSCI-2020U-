package org.openjfx;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * ServerThread is getting the board state for the Server
 */

public class ServerThread implements Runnable {
    protected Socket send = null , receive = null;
    protected List<List> board = new ArrayList<>();

    public ServerThread(Socket client1, Socket client2, List<List> board) {
        this.send = client1;
        this.receive = client2;
        this.board = board;
    }

    public void run() {
        BufferedWriter toSender = null;
        BufferedWriter toReceiver = null;
        BufferedReader clientInput = null;
        Socket temp = null;


        try {
            while(true){
                //get output to sender and receiver
                toSender = new BufferedWriter(new OutputStreamWriter(send.getOutputStream()));
                toReceiver = new BufferedWriter(new OutputStreamWriter(receive.getOutputStream()));
                clientInput = new BufferedReader(new InputStreamReader(send.getInputStream()));
                String input = clientInput.readLine();
                input = input.replaceAll("[^0-2]", "");
                System.out.println(input);
                int count = 0;
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 6; j++) {
                        board.get(i).set(j, Character.getNumericValue(input.charAt(count)));

                        count++;
                    }
                }
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
