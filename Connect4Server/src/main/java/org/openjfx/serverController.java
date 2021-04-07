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
        ThreadPoolExecutor pool = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);
        pool.execute(new ThreadPool());
    }

    @FXML
    public void exitClient(){
        System.exit(0);
    }

    class ThreadPool implements Runnable{
        ThreadPool(){

        }
        @Override
        public void run() {
            ThreadPoolExecutor pool = (ThreadPoolExecutor)Executors.newFixedThreadPool(25);
            try (var listener = new ServerSocket(47293)) {
                //System.out.println("The server is running...");
                while (true) {
                    pool.execute(new Instance(listener.accept()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    class Instance implements Runnable {
        private Socket socket;

        Instance(Socket socket) {
            //System.out.println("new thread created");
            this.socket = socket;
        }

        @Override
        public void run() {
            //System.out.println("Connected: " + socket);
            try {

            }
            catch (Exception e) {
                System.out.println(e);
            }
            finally{
                try{
                    socket.close();
                }
                catch (IOException e){

                }
                //System.out.println("Closed: " + socket);
            }
        }
    }
}
