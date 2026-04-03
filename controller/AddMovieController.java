package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

import static com.example.demo1.MyApplication.movielist;
import static com.example.demo1.MyApplication.server;

public class AddMovieController {

    private Stage stage;
    public TextField movieNameField;
    public TextField movieGenreField;
    public TextField movieReleaseYearField;
    public TextField movieBudgetField;
    public TextField movieRevenueField;

    public TextField movieRunningTimeField;
    public Label addMovielLabel;

    public void onAddClick(ActionEvent event) throws IOException {
        String movieName = movieNameField.getText();
        String movieGenre = movieGenreField.getText();
        int movieReleaseYear = Integer.parseInt(movieReleaseYearField.getText());
        int movieRunningTime = Integer.parseInt(movieRunningTimeField.getText());
        long movieBudget = Integer.parseInt(movieBudgetField.getText());
        long movieRevenue = Integer.parseInt(movieRevenueField.getText());
        long movieProfit = movieRevenue-movieBudget;
        Movie movie = new Movie(movieName,movieReleaseYear,movieGenre,movieRunningTime,StartMenuController.name,movieBudget,movieRevenue,movieProfit);

        server.write(new DataWrapper("Add", movie));
        movielist.add(movie);
        addMovielLabel.setText("Movie Added !! ");
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
