package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    public static List<Movie>movielist = new ArrayList<>();
    public static SocketWrapper server;
    public List<Movie> getMovielist() {
        return movielist;
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(MyApplication.class.getResource("StartMenu.fxml"));
        Scene scene = new Scene(loader.load(), 905, 510);
        stage.setTitle("IMDB v1.0");
        stage.setScene(scene);
        stage.show();
        server = new SocketWrapper("127.0.0.1", 3000);

    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}