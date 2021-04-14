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

    /**
     * Initializes the win screen with popup message
     */
    @FXML
    public void initialize(){
        winLabel.setText("Player " + winPlayer + " has won the game");
    }

    /**
     * Sets player that won to the player it is sent
     * @param player that made the last move
     */
    public static void setPlayer(int player) {
        winPlayer = player;
    }

    /**
     * Exits the has won window
     * @param event the press of the exit button
     */
    @FXML
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
