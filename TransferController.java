package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.demo1.MyApplication.movielist;
import static com.example.demo1.MyApplication.server;

public class TransferController implements Initializable {


    public TableColumn<Movie,String> movieName;
    public TableView table;
    public TextField companyNameField;
    public TextField movieNameField;

    public Stage stage;

    @Override

    public void initialize(URL location, ResourceBundle resources) {
        movieName.setCellValueFactory(new PropertyValueFactory<>("title"));
        table.getItems().addAll(movielist);

    }
    public void onTransferClick(ActionEvent event) throws IOException {

        String movieName = movieNameField.getText();
        String companyName = companyNameField.getText();
        for(Movie m : movielist){
            if(m.getTitle().equalsIgnoreCase(movieName)){
                m.setProductionCompany(companyName);
                server.write(new DataWrapper("Transfer",new TransferData(StartMenuController.name,m,companyName)));
                movielist.remove(m);
                break;
            }
        }
        table.getItems().clear();
        table.getItems().addAll(movielist);

    }

    public void onBackClick(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MovieList.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("IMDB v1.0");
        stage.setScene(new Scene(root, 905,510));
        stage.show();
    }
}
