package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import static com.example.demo1.MyApplication.movielist;
import static com.example.demo1.MyApplication.server;


public class StartMenuController{

    public TextField companyNameField;
    private Stage stage;
    public static String name;

    public void onClickEnter(ActionEvent event) throws IOException {
        name = companyNameField.getText();

        server.write(new DataWrapper("Name",name));
        server.write(new DataWrapper("Login",name));
        new Thread(()-> {
            try {

                Object data = server.read();
                List<Movie> movies;
                movies = (List) data;
                movielist.addAll(movies);

            } catch(IOException e){
                System.out.println("Server Disconnected!");
            } catch(ClassNotFoundException e){
                throw new RuntimeException();
            }
        }).start();

        FXMLLoader loader = new FXMLLoader(MyApplication.class.getResource("MovieList.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("IMDB v1.0");
        stage.setScene(new Scene(loader.load(), 905,510));
        stage.show();


    }
    public void onExitClick(ActionEvent event) {
        System.exit(0);
    }
}
