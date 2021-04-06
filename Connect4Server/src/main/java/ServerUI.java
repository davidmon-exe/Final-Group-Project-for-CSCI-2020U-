package java;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ServerUI extends Application {
    public ServerUI(){
        String[] args = new String[] {""};
        main(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent nRoot = FXMLLoader.load(getClass().getResource("server.fxml"));
        primaryStage.setTitle("Server Thing");
        primaryStage.setScene(new Scene(nRoot,400,600));
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}

