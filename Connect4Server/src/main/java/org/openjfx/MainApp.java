package org.openjfx;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent nRoot = FXMLLoader.load(getClass().getResource("scene.fxml"));
        primaryStage.setTitle("Server");
        Scene mainScene = new Scene(nRoot, 400, 400);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}