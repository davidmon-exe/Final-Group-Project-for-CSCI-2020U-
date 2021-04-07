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


/**
 * Controller and main source of code for the File Share Client.
 */
public class FXMLController {
    @FXML private Canvas mainCanvas;
    @FXML private GraphicsContect gc;

    @FXML
    public void initialize(){
        gc = mainCanvas.getGraphicsContext2D();
        gc.fillRect(0,40,500,500);
    }

    @FXML
    public void exitClient(){
        System.exit(0);
    }
}
