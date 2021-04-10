package org.openjfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.text.BreakIterator;

public class WinController {

    @FXML public Label winLabel;
    @FXML public Button exitButton;

    public static int winPlayer;

    @FXML
    public void initialize(){
        winLabel.setText("Player " + winPlayer + " has won the game");
    }

    public static void setPlayer(int player) {
        winPlayer = player;
    }

    @FXML
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
