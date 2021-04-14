package org.openjfx;
import java.io.*;
import java.net.*;
import java.util.*;

public class ServerThread extends Thread {
    protected Socket send = null , receive = null;
    protected List<List> board = new ArrayList<>();

    /**
     * Sets the clients to current clients
     * Sets board to the current board
     * @param board      the board as a List
     * @param client1    a client connection
     * @param client2    a second client connection
     */
    public ServerThread(Socket client1, Socket client2, List<List> board) {
        this.send = client1;
        this.receive = client2;
        this.board = board;
    }

    /**
     * A run function that evaluates the players board                                    (ADD TO THIS?)
     * Then sends the board to the other client to update it on the most recent move
     */
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
    public void fetchList(String input) throws IOException {
        input = input.replaceAll("[^0-2]", "");
        System.out.println(input);
        int count = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                board.get(i).set(j, Character.getNumericValue(input.charAt(count)));

                count++;
            }
        }
    }

    /**
     * Checks winning moves horizontally, vertically and on all diagonals
     * Does this by checking the numbers in the positions of the ArrayList
     * @param row     rolumn number of piece most recently placed
     * @param col     row number of piece most recently placed
     * @throws IOException
     */
    public boolean checkWin(int row, int col ) throws IOException {
        int player = 1;
        System.out.println("row is: " + row + "\ncol is: " + col + "\nplayer: " + player);
        int count = 1;
        final int connect4 = 4;


        //check vertical (checking from top to bottom only)
        for (int i = 1; i < connect4; i++) {
            if (row-i >= 0) {
                if (board.get(col).get(row-i).equals(player)) {
                    count++;
                }
            }
            System.out.println("Vertical: " + count);
        }
        if (count >= 4) {
            System.out.println("Player " + player + " won");
            return true;
        } else count = 1;

        //check horizontal (all the way left then all the way right)
        for (int i = 1; i < connect4; i++) {
            if (col-i >= 0) {
                if (board.get(col-i).get(row).equals(player)) {
                    count++;
                }
            }

            if (col+i <= 6) {
                if (board.get(col+i).get(row).equals(player)) {
                    count++;
                }
            }
            System.out.println("Horizontal: " + count);
        }
        if (count >= 4) {
            System.out.println("Player " + player + " won");
            return true;
        } else count = 1;


        //check diagonals (gotta check 4 ways, 2 for each for loop)
        for (int i = 1; i < connect4; i++) {
            if (col-i >= 0 && row-i >= 0) {
                if (board.get(col-i).get(row-i).equals(player)) {
                    count++;
                } else break;
            } else break;
            if (col+i <= 6 && row+i <= 5) {
                if (board.get(col+i).get(row+i).equals(player)) {
                    count++;
                }
            }
            System.out.println("First Diagonal: " + count);
        }
        if (count >= 4) {
            System.out.println("Player " + player + " won");
            return true;
        } else count = 1;


        //second diagonal
        for (int i = 1; i < connect4; i++) {
            if (col+i >= 0 && row-i >= 0) {
                if (col+i == board.size()) {
                    break;
                }
                if (board.get(col+i).get(row-i).equals(player)) {
                    count++;
                }
            } else break;
            if (col-i >= 0 && row+i >= 0) {
                if (row+i == 6) {
                    break;
                }
                if (board.get(col-i).get(row+i).equals(player)) {
                    count++;
                }
            }
            System.out.println("Second Diagonal: " + count);
        }
        if (count >= 4) {
            System.out.println("Player " + player + " won");
            return true;
        } else return false;
    }


}
