package org.openjfx;

import java.io.*;
import java.net.*;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class FXMLController {
    @FXML public TextField nameField;
    @FXML private Canvas mainCanvas;
    @FXML private GraphicsContext gc;
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final int ROW_INCREMENT = 120;
    private static final int COLUMN_INCREMENT = 80;

    //create list
    public List<List> boardStateArray = new ArrayList<>();

    private int turnCounter = 0;

    public Socket socket;
    public BufferedWriter serverOut;
    public BufferedReader serverIn;
    public String name;
    public String reply;
    public boolean isFirst;
    public String opponent;

    /**
     * Initializes the program with a canvas and array
     * @throws IOException
     */
    @FXML
    public void initialize() throws IOException {

        gc = mainCanvas.getGraphicsContext2D();
        createArray();
        drawBoard();

    }

    /**
     * Connects client to server and link both to same game
     * @param event when the connect button is pressed
     * @throws IOException
     */
    @FXML
    public void connectionHandler(ActionEvent event) throws IOException {
        Runnable task = () -> {
            try {
                //while (true) {
                socket = new Socket("localhost", 11111);
                serverOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                serverOut.write("CONNECT "+ nameField.getText());
                System.out.println("Connection made.");
                serverOut.write("CONNECT "+ nameField.getText());
                serverOut.flush();
                reply = serverIn.readLine();
                isFirst = false;

                if (reply.equals("100")) {
                    isFirst = true;
                    reply = serverIn.readLine();
                }
                opponent = reply.substring(4);
                System.out.println("You have been matched with " + opponent + ".");


                if(!isFirst){
                    System.out.println("Waiting for " + opponent+ "\'s move . . .");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    /**
     * Sends the board to the server as a string
     * @throws IOException
     */
    public void sendBoard() throws IOException {
        System.out.println("Sending Board");
        serverOut.write(boardStateArray.toString());
        serverOut.flush();
    }



    /**
     * Gets the new board state from the server
     * @throws IOException
     */
    public void fetchBoard() throws IOException {
        //[[1, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0], [1, 0, 0, 0, 0, 0], [2, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0], [2, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0]]
        //1000000000001000002000000000002000000000000
        String input = serverIn.readLine();
        input = input.replaceAll("[^0-2]","");
        System.out.println(input);
        int count = 0;
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                boardStateArray.get(i).set(j, Character.getNumericValue(input.charAt(count)));
                count++;
            }
        }
    }

    /**
     * Draws the board based on the boardState ArrayList
     * Checks if the position is a 0, 1 or 2
     */
    public void drawBoard(){
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0,40,900,520);

        for (int i = 0; i < COLUMNS; i++){
            for (int j = 0; j < ROWS; j++){
                //fill columns
                if((int)boardStateArray.get(i).get(j) == 0){
                    gc.setFill(Color.WHITE);
                    gc.fillArc(45 + (i * ROW_INCREMENT),455 - (j * COLUMN_INCREMENT),75,75,0,360,ArcType.ROUND);
                }
                else if((int)boardStateArray.get(i).get(j) == 1){
                    gc.setFill(Color.RED);
                    gc.fillArc(45 + (i * ROW_INCREMENT),455 - (j * COLUMN_INCREMENT),75,75,0,360,ArcType.ROUND);
                }
                else if((int)boardStateArray.get(i).get(j) == 2){
                    gc.setFill(Color.YELLOW);
                    gc.fillArc(45 + (i * ROW_INCREMENT),455 - (j * COLUMN_INCREMENT),75,75,0,360,ArcType.ROUND);
                }
            }
        }
    }


    /**
     * Creates Arraylist of 7x6 filled with 0s
     */
    public void createArray(){
        //give boardStateArray lists
        List<Integer> temp1 = Arrays.asList(0, 0, 0, 0, 0, 0);
        List<Integer> temp2 = Arrays.asList(0, 0, 0, 0, 0, 0);
        List<Integer> temp3 = Arrays.asList(0, 0, 0, 0, 0, 0);
        List<Integer> temp4 = Arrays.asList(0, 0, 0, 0, 0, 0);
        List<Integer> temp5 = Arrays.asList(0, 0, 0, 0, 0, 0);
        List<Integer> temp6 = Arrays.asList(0, 0, 0, 0, 0, 0);
        List<Integer> temp7 = Arrays.asList(0, 0, 0, 0, 0, 0);

        boardStateArray.add(temp1);
        boardStateArray.add(temp2);
        boardStateArray.add(temp3);
        boardStateArray.add(temp4);
        boardStateArray.add(temp5);
        boardStateArray.add(temp6);
        boardStateArray.add(temp7);
        System.out.println(boardStateArray);
    }

    /**
     * Prints a circle in the column chosen
     * base colour off of player turn
     * @param event  pressing the button
     * @throws IOException
     */
    @FXML
    public void dropButtons(ActionEvent event) throws IOException {
        //when a drop button is pressed drop it on desired area
        final int btnId = Integer.parseInt(((Control)event.getSource()).getId());
        boolean hasWon = false;
        for (int i = 0; i < COLUMNS; i++) {
            if (i == btnId-1) {
                System.out.println("Button " + (btnId));
                System.out.println(boardStateArray.size());
                for (int j = 0; j < ROWS; j++) {
                    if (boardStateArray.get(btnId-1).get(j).equals(0)) {
                        boardStateArray.get(btnId-1).set(j,(turnCounter % 2) + 1);
                        // the (turnCounter % 2) + 1 is just seeing what player it is
                        hasWon = checkWin(j, i, ((turnCounter % 2) + 1));
                        if (turnCounter % 2 <= 0) {
                            gc.setFill(Color.RED);
                        } else {
                            gc.setFill(Color.YELLOW);
                        }
                        gc.fillArc(45 + (i * ROW_INCREMENT),455 - (j * COLUMN_INCREMENT),75,75,0,360,ArcType.ROUND);
                        //sendBoard();
                        break;
                    }
                }
                System.out.println(boardStateArray);
            }
        }

        turnCounter++;
    }

    /**
     * Checks winning moves horizontally, vertically and on all diagonals
     * Does this by checking the numbers in the positions of the ArrayList
     * @param player  the player number of piece placed
     * @param row     rolumn number of piece most recently placed
     * @param col     row number of piece most recently placed
     * @throws IOException
     */
    public boolean checkWin(int row, int col, int player) throws IOException {
        System.out.println("row is: " + row + "\ncol is: " + col + "\nplayer: " + player);
        int count = 1;
        final int connect4 = 4;

        //check vertical (checking from top to bottom only)
        for (int i = 1; i < connect4; i++) {
            if (row-i >= 0) {
                if (boardStateArray.get(col).get(row-i).equals(player)) {
                    count++;
                }
            }
            System.out.println("Vertical: " + count);
        }
        if (count >= 4) {
            System.out.println("Player " + player + " won");
            win(player);
            return true;
        } else count = 1;


        //check horizontal (all the way left then all the way right)
        for (int i = 1; i < connect4; i++) {
            if (col-i >= 0) {
                if (boardStateArray.get(col-i).get(row).equals(player)) {
                    count++;
                }
            }

            if (col+i <= 6) {
                if (boardStateArray.get(col+i).get(row).equals(player)) {
                    count++;
                }
            }
            System.out.println("Horizontal: " + count);
        }
        if (count >= 4) {
            System.out.println("Player " + player + " won");
            win(player);
            return true;
        } else count = 1;


        //check diagonals (gotta check 4 ways, 2 for each for loop)
        for (int i = 1; i < connect4; i++) {
            if (col-i >= 0 && row-i >= 0) {
                if (boardStateArray.get(col-i).get(row-i).equals(player)) {
                    count++;
                } else break;
            } else break;
            if (col+i <= 6 && row+i <= 5) {
                if (boardStateArray.get(col+i).get(row+i).equals(player)) {
                    count++;
                }
            }
            System.out.println("First Diagonal: " + count);
        }
        if (count >= 4) {
            System.out.println("Player " + player + " won");
            win(player);
            return true;
        } else count = 1;


        //second diagonal
        for (int i = 1; i < connect4; i++) {
            if (col+i >= 0 && row-i >= 0) {
                if (col+i == boardStateArray.size()) {
                    break;
                }
                if (boardStateArray.get(col+i).get(row-i).equals(player)) {
                    count++;
                }
            } else break;
            if (col-i >= 0 && row+i >= 0) {
                if (row+i == 6) {
                    break;
                }
                if (boardStateArray.get(col-i).get(row+i).equals(player)) {
                    count++;
                }
            }
            System.out.println("Second Diagonal: " + count);
        }
        if (count >= 4) {
            System.out.println("Player " + player + " won");
            win(player);
            return true;
        } else return false;
    }

    /**
     * Creates a new scene to display who the winner is and to allow the user to exit the system
     * @param player  the player number of piece placed
     * @throws IOException
     */
    public void win(int player) throws IOException {
        WinController.setPlayer(player);
        FXMLLoader loadWinScreen = new FXMLLoader();
        loadWinScreen.setLocation(getClass().getResource("winScreen.fxml"));
        Scene winScene = new Scene(loadWinScreen.load(), 500, 400);
        Stage winStage = new Stage();
        winStage.setTitle("Winner");
        winStage.setScene(winScene);
        winStage.show();
    }
}
