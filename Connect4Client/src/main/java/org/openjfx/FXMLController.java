package org.openjfx;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import static java.nio.file.StandardCopyOption.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;


/**
 * Controller and main source of code for the File Share Client.
 */
public class FXMLController {
    @FXML private Canvas mainCanvas;
    @FXML private GraphicsContext gc;
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final int ROW_INCREMENT = 120;
    private static final int COLUMN_INCREMENT = 80;

    //create list
    List<List> boardStateArray = new ArrayList<>();

    private int turnCounter = 0;

    @FXML
    public void initialize(){
        gc = mainCanvas.getGraphicsContext2D();
        drawBoard();
        createArray();
    }

    public void drawBoard(){
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0,40,900,520);
        gc.setFill(Color.WHITE);

        for (int i = 0; i < COLUMNS; i++){
            for (int j = 0; j < ROWS; j++){
                //fill columns
                gc.fillArc(45 + (i * ROW_INCREMENT),55 + (j * COLUMN_INCREMENT),75,75,0,360,ArcType.ROUND);
            }
        }

    }
    /**
     * TODO:
     *   Short:
     *       Make a 2D list
     *       Lists within one big list more so
     *       Attach buttons to lists(?)
     *   Long:
     *       Make a list for each column, filled with 0s
     *       Lists all going into one big list
     *       Every time you press a drop button
     *       a circle is filled, and in the array where it simulates the board state
     *       fill in a 1 or 2 where the circle is located as well
     *       To assign values do the first index as the bottom row in the game
     */

    public void createArray(){
        /**
         * FIXME:
         *      Change createArray() and it's hard code
         *      Issue is, trying to make 1 ArrayList,
         *      then assigning many times with a for loop does not work.
         *      The reason is because the ID of the ArrayList is still the same.
         *      So, in dropButtons, changing the value of the ArrayList changes
         *      all of them, because inherently, they *are* the same list
         */
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

    @FXML
    public void dropButtons(ActionEvent event){
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
                        break;
                    }
                }
                System.out.println(boardStateArray);
            }
        }
        if (hasWon) {
            /**
             * This is if we want to do something like this
             * Maybe a bit excessive, but this is just in case
             */
        }
        turnCounter++;
    }

    /*
     * Takes the arrayList and check all the win conditions
     * 4 in a row vertically, horizontally or diagonally
     * Should be called after each button press
     * If win condition is met, display winning player
     */
    public boolean checkWin(int row, int col, int player) {
        /**
         * Hopefully this can be better implemented but this is a working prototype
         * Could either clean this one up, or do something approaching from another direction
         */
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
        }
        if (count >= 4) {
            System.out.println("Player " + player + " won");
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
        }
        if (count >= 4) {
            System.out.println("Player " + player + " won");
            return true;
        } else count = 1;


        //check diagonals (gotta check 4 ways, 2 for each for loop)
        for (int i = 1; i < connect4; i++) {
            if (col-i >= 0 && row-i >= 0) {
                if (boardStateArray.get(col-i).get(row-i).equals(player)) {
                    count++;
                } else break;
            } else break;
            if (col+i >= 0 && row+i >= 0) {
                if (boardStateArray.get(col+i).get(row+i).equals(player)) {
                    count++;
                }
            }
        }
        if (count >= 4) {
            System.out.println("Player " + player + " won");
            return true;
        } else count = 1;


        //second diagonal
        for (int i = 1; i < connect4; i++) {
            if (col+i >= 0 && row-i >= 0) {
                if (boardStateArray.get(col+i).get(row-i).equals(player)) {
                    count++;
                }
            } else break;
            if (col-i >= 0 && row+i >= 0) {
                if (boardStateArray.get(col-i).get(row+i).equals(player)) {
                    count++;
                }
            }
        }
        if (count >= 4) {
            System.out.println("Player " + player + " won");
            return true;
        } else return false;

    }
}
