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

    @FXML
    public void initialize(){
        gc = mainCanvas.getGraphicsContext2D();
        drawBoard();
    }

    public void drawBoard(){
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0,40,900,520);
        gc.setFill(Color.WHITE);

        final int ROW_INCREMENT = 120;
        final int COLUMN_INCREMENT = 80;

        for (int i = 0; i < COLUMNS; i++){
            for (int j = 0; j < ROWS; j++){
                //fill columns
                gc.fillArc(45 + (i * ROW_INCREMENT),55 + (j * COLUMN_INCREMENT),75,75,0,360,ArcType.ROUND);
            }
        }

    }
    /* TODO:
        Short:
            Make a 2D list
            Lists within one big list more so
            Attach buttons to lists(?)
        Long:
            Make a list for each column, filled with 0s
            Lists all going into one big list
            Every time you press a drop button
            a circle is filled, and in the array where it simulates the board state
            fill in a 1 or 2 where the circle is located as well
     */

    @FXML
    public void exitClient(){
        System.exit(0);
    }
}
