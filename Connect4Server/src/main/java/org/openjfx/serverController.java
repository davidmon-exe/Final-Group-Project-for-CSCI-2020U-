package org.openjfx;
import javafx.fxml.FXML;
import java.util.concurrent.*;
import javafx.scene.control.TextArea;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class serverController {
    @FXML
    private TextArea text;

    @FXML
    public void initialize() throws IOException {
//        ThreadPoolExecutor pool = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);
//        pool.execute(new ThreadPool());
    }

    @FXML
    public void exitClient(){
        System.exit(0);
    }

}
