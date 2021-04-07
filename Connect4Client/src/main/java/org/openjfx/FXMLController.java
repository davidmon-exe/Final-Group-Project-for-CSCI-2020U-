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

    @FXML
    public void initialize(){
        gc = mainCanvas.getGraphicsContext2D();
        drawBoard();
    }

    public void drawBoard(){
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0,40,900,520);
        gc.setFill(Color.WHITE);
        //first column
        gc.fillArc(45,55,75,75,0,360,ArcType.ROUND);
        gc.fillArc(45,135,75,75,0,360,ArcType.ROUND);
        gc.fillArc(45,215,75,75,0,360,ArcType.ROUND);
        gc.fillArc(45,295,75,75,0,360,ArcType.ROUND);
        gc.fillArc(45,375,75,75,0,360,ArcType.ROUND);
        gc.fillArc(45,455,75,75,0,360,ArcType.ROUND);
        //second column
        gc.fillArc(165,55,75,75,0,360,ArcType.ROUND);
        gc.fillArc(165,135,75,75,0,360,ArcType.ROUND);
        gc.fillArc(165,215,75,75,0,360,ArcType.ROUND);
        gc.fillArc(165,295,75,75,0,360,ArcType.ROUND);
        gc.fillArc(165,375,75,75,0,360,ArcType.ROUND);
        gc.fillArc(165,455,75,75,0,360,ArcType.ROUND);
        //third column
        gc.fillArc(285,55,75,75,0,360,ArcType.ROUND);
        gc.fillArc(285,135,75,75,0,360,ArcType.ROUND);
        gc.fillArc(285,215,75,75,0,360,ArcType.ROUND);
        gc.fillArc(285,295,75,75,0,360,ArcType.ROUND);
        gc.fillArc(285,375,75,75,0,360,ArcType.ROUND);
        gc.fillArc(285,455,75,75,0,360,ArcType.ROUND);
        //fourth column
        gc.fillArc(405,55,75,75,0,360,ArcType.ROUND);
        gc.fillArc(405,135,75,75,0,360,ArcType.ROUND);
        gc.fillArc(405,215,75,75,0,360,ArcType.ROUND);
        gc.fillArc(405,295,75,75,0,360,ArcType.ROUND);
        gc.fillArc(405,375,75,75,0,360,ArcType.ROUND);
        gc.fillArc(405,455,75,75,0,360,ArcType.ROUND);
        //fifth column
        gc.fillArc(525,55,75,75,0,360,ArcType.ROUND);
        gc.fillArc(525,135,75,75,0,360,ArcType.ROUND);
        gc.fillArc(525,215,75,75,0,360,ArcType.ROUND);
        gc.fillArc(525,295,75,75,0,360,ArcType.ROUND);
        gc.fillArc(525,375,75,75,0,360,ArcType.ROUND);
        gc.fillArc(525,455,75,75,0,360,ArcType.ROUND);
        //sixth column
        gc.fillArc(645,55,75,75,0,360,ArcType.ROUND);
        gc.fillArc(645,135,75,75,0,360,ArcType.ROUND);
        gc.fillArc(645,215,75,75,0,360,ArcType.ROUND);
        gc.fillArc(645,295,75,75,0,360,ArcType.ROUND);
        gc.fillArc(645,375,75,75,0,360,ArcType.ROUND);
        gc.fillArc(645,455,75,75,0,360,ArcType.ROUND);
        //seventh column
        gc.fillArc(765,55,75,75,0,360,ArcType.ROUND);
        gc.fillArc(765,135,75,75,0,360,ArcType.ROUND);
        gc.fillArc(765,215,75,75,0,360,ArcType.ROUND);
        gc.fillArc(765,295,75,75,0,360,ArcType.ROUND);
        gc.fillArc(765,375,75,75,0,360,ArcType.ROUND);
        gc.fillArc(765,455,75,75,0,360,ArcType.ROUND);
    }

    @FXML
    public void exitClient(){
        System.exit(0);
    }
}
